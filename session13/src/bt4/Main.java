// File: BenhNhanDashboardRepository.java
package bt4;

import utils.DatabaseConnection;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {

    public List<BenhNhanDTO> findBenhNhanCapCuuTrongNgay(Date ngay) throws SQLException {
        String sql =
                "SELECT " +
                        "  b.ma_benh_nhan, " +
                        "  b.ho_ten, " +
                        "  b.ngay_nhap_vien, " +
                        "  b.gioi_tinh, " +
                        "  d.ma_dich_vu, " +
                        "  d.ten_dich_vu, " +
                        "  d.loai_dich_vu, " +
                        "  d.thoi_gian_su_dung " +
                        "FROM BenhNhan b " +
                        "LEFT JOIN DichVuSuDung d ON b.ma_benh_nhan = d.ma_benh_nhan " +
                        "WHERE b.khoa = 'CAP_CUU' " +
                        "  AND b.trang_thai = 'DANG_NAM' " +
                        "  AND DATE(b.ngay_nhap_vien) = ? " +
                        "ORDER BY b.ma_benh_nhan";

        Map<Integer, BenhNhanDTO> map = new LinkedHashMap<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, ngay);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int maBN = rs.getInt("ma_benh_nhan");

                    // Lấy hoặc tạo mới BenhNhanDTO trong map
                    BenhNhanDTO bn = map.get(maBN);
                    if (bn == null) {
                        bn = new BenhNhanDTO();
                        bn.setMaBenhNhan(maBN);
                        bn.setHoTen(rs.getString("ho_ten"));
                        bn.setNgayNhapVien(rs.getDate("ngay_nhap_vien"));
                        bn.setGioiTinh(rs.getString("gioi_tinh"));
                        bn.setDsDichVu(new ArrayList<>());  // luôn KHỞI TẠO LIST để tránh NPE

                        map.put(maBN, bn);
                    }

                    // ==========================
                    // BẪY 2: BỆNH NHÂN KHÔNG CÓ DỊCH VỤ
                    // ==========================
                    // Do dùng LEFT JOIN, các cột dịch vụ có thể NULL.
                    // Nếu ma_dich_vu == null => bệnh nhân chưa có dịch vụ nào.
                    // => KHÔNG add DichVu, giữ dsDichVu là LIST RỖNG (không null).
                    Object maDichVuObj = rs.getObject("ma_dich_vu");
                    if (maDichVuObj != null) {
                        DichVu dv = new DichVu();
                        dv.setMaDichVu((Integer) maDichVuObj);
                        dv.setTenDichVu(rs.getString("ten_dich_vu"));
                        dv.setLoaiDichVu(rs.getString("loai_dich_vu"));
                        dv.setThoiGianSuDung(rs.getTimestamp("thoi_gian_su_dung"));

                        bn.getDsDichVu().add(dv);
                    }
                    // Nếu ma_dich_vuObj == null: không làm gì.
                }
            }
        }

        return new ArrayList<>(map.values());
    }
}
