
package exam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductService {
    private final List<Product> list = new ArrayList<>();

    public void addProduct(Product product) {
        list.add(product);
    }

    public List<Product> getProducts() {
        return list;
    }


    public Product findById(int id) {
        for (Product p : list) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public boolean updateQuantity(int id, int newQuantity) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setQuantity(newQuantity);
        return true;
    }


    public void deleteOutOfStock() {
        Iterator<Product> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getQuantity() == 0) {
                it.remove();
            }
        }
    }
}
