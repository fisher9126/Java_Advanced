package exam;

import java.util.Scanner;

public class FactoryProduct {
    public Product getProduct(String type){
        Scanner sc = new Scanner(System.in);
        Product product = null;
        System.out.print("Nhập ID sản phẩm: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();
        System.out.print("Nhập giá tiền sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());
        switch (type){
            case "Physical":


                System.out.print("Nhập trọng lượng của sản phẩm: ");
                double weight=Double.parseDouble(sc.nextLine());
                product=new PhysicalProduct(id, name, price, weight);
                break;
            case "Digital":
                System.out.print("Nhập dung lượng của sản phẩm: ");
                double size=Double.parseDouble(sc.nextLine());
                product=new DigitalProduct(id, name, price, size);
                break;
            default:
                break;

        }
        return product;
    }
}
