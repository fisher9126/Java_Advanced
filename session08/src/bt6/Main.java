package bt6;

public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new WebsiteFactory();

        double price = factory.createDiscount().apply(1000);
        factory.createPayment().pay(price);
        factory.createNotification().notifyUser();
    }
}