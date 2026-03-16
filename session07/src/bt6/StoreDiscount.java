package bt6;

public class StoreDiscount implements DiscountStrategy {

    public double apply(double total){
        return total * 0.95;
    }
}