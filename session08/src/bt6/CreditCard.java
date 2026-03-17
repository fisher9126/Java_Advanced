package bt6;

public class CreditCard implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán: " + amount);
    }
}