
package exam;

import utils.DBConnection;

import java.sql.*;

public class Transfer {


    public boolean withDraw(String id, double amount) {

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        double balance = rs.getDouble("Balance");
                        if (balance < amount) {
                            System.err.println("Số dư không đủ để chuyển khoản...");
                            return false;
                        } else {
                            String withDrawSql =
                                    "UPDATE Accounts SET Balance = Balance - ? WHERE AccountId = ?";
                            try (PreparedStatement ps1 = con.prepareStatement(withDrawSql)) {
                                ps1.setDouble(1, amount);
                                ps1.setString(2, id);
                                int rows = ps1.executeUpdate();
                                if (rows > 0) {
                                    System.out.println("Trừ tiền thành công");
                                    return true;
                                } else {
                                    System.err.println("Không tìm thấy tài khoản để trừ tiền.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        System.err.println("Không tìm thấy tài khoản: " + id);
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


    public void deposit(String id, double amount) {

        try (Connection con = DBConnection.getConnection()) {

            String callSql = "{CALL sp_UpdateBalance(?, ?)}";
            try (CallableStatement cs = con.prepareCall(callSql)) {
                cs.setString(1, id);
                cs.setDouble(2, amount);
                int rows = cs.executeUpdate();
                if (rows > 0) {
                    System.out.println("Nhận tiền thành công");
                } else {

                    System.out.println("Không thanành công");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
