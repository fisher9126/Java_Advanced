package exam;

public class PhysicalProduct extends Product implements Factory {
    double weight;

    public PhysicalProduct(double weght) {
        this.weight = weight;
    }

    public PhysicalProduct(String id, String name, Double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }
    public void displayInfo(){
        System.out.print("ID: " + this.getId());
        System.out.print(" |Name: " + this.getName());
        System.out.print(" |Price: " + this.getPrice());
        System.out.println(" |Weight: " + weight);
    }
    @Override
    public void createProduct(Product product) {
        System.out.println("Creating product " + product.getName());
    }
}
