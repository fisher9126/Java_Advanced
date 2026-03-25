package exam;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static void main(String[] args) {
        Connection con= DBConnection.getConnection();
        Transfer transfer = new Transfer();
        try{
            con.setAutoCommit(false);
            String receiveId="AC002";
            String sendId="ACC01";
            Double amount=1000.0;
            if(transfer.withDraw(sendId,amount)){
                transfer.deposit(receiveId,amount);
            }else{
                System.out.println("Chuyển tiền không thành công");
            }

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }





}
