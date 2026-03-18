package exam;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    private static List<Product> products;

    private ProductData() {
    }
    public static List<Product> getProducts() {
        if(products==null){
            products=new ArrayList<>();
        }
        return products;
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public void UpdateProduct(Product product,int index){
        products.add(product);
    }
}
