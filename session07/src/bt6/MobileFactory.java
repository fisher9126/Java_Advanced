package bt6;


public class MobileFactory implements SalesChannelFactory{

    public DiscountStrategy createDiscount(){
        return new PercentageDiscount(0.15);
    }

    public PaymentMethod createPayment(){
        return new MomoPayment();
    }

    public NotificationService createNotification(){
        return new PushNotification();
    }
}