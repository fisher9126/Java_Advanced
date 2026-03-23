package bt4;

import utils.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
//        List<String> list = Arrays.asList("A", "B", "C");
//
//        for (String data : list) {
//            String sql = "INSERT INTO Results(data) VALUES('" + data + "')";
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(sql);
//
//            //  LỖI: tạo Statement + parse SQL mỗi vòng lặp → cực chậm
//        }

        List<String> list = Arrays.asList("A", "B", "C");

        String sql = "INSERT INTO Results(data) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (String data : list) {
            ps.setString(1, data);
            ps.executeUpdate();
        }

        //  FIX:
        // - SQL chỉ parse 1 lần
        // - reuse execution plan → nhanh hơn nhiều
    }
}