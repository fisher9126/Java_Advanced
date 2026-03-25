package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/banking";
    private static final String User="root";
    private static final String Password="12345678";
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,User,Password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
return conn;
    }
    public static void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
