package bt6;

public class MobileDiscount implements DiscountStrategy {

    public double apply(double total){
        return total * 0.85;
    }
}