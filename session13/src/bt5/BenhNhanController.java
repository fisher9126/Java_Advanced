// BenhNhanController.java
package bt5;

import utils.DatabaseConnection;

import java.sql.*;

public class BenhNhanController {

    /**
     * Tiếp nhận bệnh nhân mới: insert bệnh nhân, cập nhật giường, ghi tài chính.
     * 3 bước này được bọc trong 1 TRANSACTION.
     */
    public void tiepNhanBenhNhan(String ten, int tuoi, String maGiuong, double soTienTamUng) throws Exception {
        Connection conn = null;
        boolean oldAutoCommit = true;

        try {
            conn = DatabaseConnection.getConnection();
            oldAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false); // BẮT ĐẦU TRANSACTION

            // 1. Insert bệnh nhân
            int maBenhNhan = insertBenhNhan(conn, ten, tuoi);

            // 2. Cập nhật giường
            int rowsGiuong = updateGiuong(conn, maGiuong, maBenhNhan);
            if (rowsGiuong == 0) {
                // Không có giường nào được update -> giường không tồn tại hoặc đã có người
                throw new Exception("Giường không tồn tại hoặc đã có người nằm. Vui lòng chọn giường khác.");
            }

            // 3. Cộng tiền vào bảng tài chính
            int rowsTC = insertTaiChinh(conn, maBenhNhan, soTienTamUng);
            if (rowsTC == 0) {
                // Lý thuyết ít khi =0, nhưng vẫn check cho đúng tinh thần Bẫy "RowAffected=0"
                throw new Exception("Ghi nhận tài chính thất bại. Vui lòng thử lại.");
            }

            // Nếu tới đây mà không có exception -> commit
            conn.commit();
        } catch (Exception e) {
            // Có lỗi -> rollback toàn bộ
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Lỗi khi rollback: " + ex.getMessage());
                }
            }
            // Ném lại lỗi để View hiển thị message thân thiện
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(oldAutoCommit);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int insertBenhNhan(Connection conn, String ten, int tuoi) throws SQLException {
        String sql = "INSERT INTO BenhNhan(ten, tuoi, created_at) VALUES (?, ?, NOW())";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, ten);
            ps.setInt(2, tuoi);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Không lấy được ID bệnh nhân mới.");
                }
            }
        }
    }

    private int updateGiuong(Connection conn, String maGiuong, int maBenhNhan) throws SQLException {
        String sql = "UPDATE Giuong " +
                "SET status = 'DA_CO_NGUOI', ma_benh_nhan = ? " +
                "WHERE ma_giuong = ? AND status = 'TRONG'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBenhNhan);
            ps.setString(2, maGiuong);
            return ps.executeUpdate();
        }
    }

    private int insertTaiChinh(Connection conn, int maBenhNhan, double soTienTamUng) throws SQLException {
        String sql = "INSERT INTO TaiChinh(ma_benh_nhan, so_tien, loai_giao_dich, thoi_gian) " +
                "VALUES (?, ?, 'TAM_UNG', NOW())";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBenhNhan);
            ps.setDouble(2, soTienTamUng);
            return ps.executeUpdate();
        }
    }

    // Hàm xem giường trống
    public void xemGiuongTrong() {
        String sql = "SELECT ma_giuong FROM Giuong WHERE status = 'TRONG'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Danh sách giường đang TRỐNG:");
            boolean hasAny = false;
            while (rs.next()) {
                System.out.println("- " + rs.getString("ma_giuong"));
                hasAny = true;
            }
            if (!hasAny) {
                System.out.println("(Không có giường trống)");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xem giường trống: " + e.getMessage());
        }
    }

}
