package bt6;

public class StorePOSFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount(){
        return new StoreDiscount();
    }

    public PaymentMethod createPayment(){
        return new CashPayment();
    }

    public NotificationService createNotification(){
        return new PrintNotification();
    }
}