package bt6;

public interface SalesChannelFactory {
    DiscountStrategy createDiscount();
    PaymentMethod createPayment();
    NotificationService createNotification();
}