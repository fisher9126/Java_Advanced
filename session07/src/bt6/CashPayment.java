package bt6;

public class CashPayment implements PaymentMethod {

    public void pay(double amount){
        System.out.println("Thanh toan tien mat: " + amount);
    }
}