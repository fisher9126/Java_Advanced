package bt5;

import utils.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. List");
            System.out.println("2. Add");
            System.out.println("3. Update");
            System.out.println("4. Discharge");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Patients");
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
                    }
                    break;

                case 2:
                    String sql = "INSERT INTO Patients(name, age, department) VALUES(?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);

                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Dept: ");
                    String dept = sc.nextLine();

                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, dept);
                    ps.executeUpdate();
                    break;

                case 3:
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    String updateSql = "UPDATE Patients SET name = ? WHERE id = ?";
                    PreparedStatement ps2 = conn.prepareStatement(updateSql);

                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();

                    ps2.setString(1, newName);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                    break;

                case 4:
                    System.out.print("ID: ");
                    int pid = sc.nextInt();

                    CallableStatement cs = conn.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");
                    cs.setInt(1, pid);
                    cs.registerOutParameter(2, Types.DOUBLE);

                    cs.execute();

                    double fee = cs.getDouble(2);
                    System.out.println("Fee: " + fee);
                    break;

                case 5:
                    return;
            }
        }
    }
}