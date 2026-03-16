package bt6;

public class Main {

    public static void main(String[] args) {

        SalesChannelFactory factory = new WebsiteFactory();

        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notification = factory.createNotification();

        double total = 1000000;

        double finalPrice = discount.apply(total);

        payment.pay(finalPrice);

        notification.send("Don hang thanh cong");
    }
}