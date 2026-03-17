package bt6;

public class TenPercentDiscount implements DiscountStrategy {
    public double apply(double price) {
        return price * 0.9;
    }
}