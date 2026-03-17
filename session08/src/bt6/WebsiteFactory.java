package bt6;

public class WebsiteFactory implements AbstractFactory {
    public DiscountStrategy createDiscount() {
        return new TenPercentDiscount();
    }

    public PaymentMethod createPayment() {
        return new CreditCard();
    }

    public NotificationService createNotification() {
        return new EmailNotification();
    }
}