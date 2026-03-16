package bt6;

public class WebsiteFactory implements SalesChannelFactory{

    public DiscountStrategy createDiscount(){
        return new PercentageDiscount(0.1);
    }

    public PaymentMethod createPayment(){
        return new CreditCardPayment();
    }

    public NotificationService createNotification(){
        return new EmailNotification();
    }
}