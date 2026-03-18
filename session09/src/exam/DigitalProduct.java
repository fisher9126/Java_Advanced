package exam;

public class DigitalProduct extends Product implements Factory {
    double size;

    public DigitalProduct(double size) {
        this.size = size;
    }

    public DigitalProduct(String id, String name, Double price, double size) {
        super(id, name, price);
        this.size = size;
    }
    public void displayInfo(){
        System.out.print("ID: "+ this.getId());
        System.out.print(" |Name: " + this.getName());
        System.out.print(" |Price: " + this.getPrice());
        System.out.println(" |Size: " + size);
    }
    @Override
    public void createProduct(Product product) {
        System.out.println("Creating product " + product.getName());
    }
}
