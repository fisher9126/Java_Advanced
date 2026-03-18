package exam;

import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        List<Product> products=ProductData.getProducts();
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("=========MENU========");
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. Display products");
            System.out.println("5. Exit");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("1. Physical Product");
                    System.out.println("2. Digital Product");
                    int choice2 = Integer.parseInt(sc.nextLine());

                    Product item=null;
                    switch (choice2) {
                        case 1:
                             item=new FactoryProduct().getProduct("Physical");
                            break;
                        case 2:
                             item=new FactoryProduct().getProduct("Digital");
                            break;

                    }
                    products.add(item);
                    System.out.println("Added product");
                    break;
                case 2:
                    String updateId=sc.nextLine();
                    System.out.println("Nhập ID muốn sửa: ");
                    if(products.stream().filter(p->p.getId().equals(Integer.parseInt(updateId))).findAny().isPresent()){
                        products.remove(Integer.parseInt(updateId));
                        if(products.get(Integer.parseInt(updateId)) instanceof PhysicalProduct){
                            products.add(new FactoryProduct().getProduct("Physical"));
                        }if(products.get(Integer.parseInt(updateId)) instanceof DigitalProduct){
                            products.add(new FactoryProduct().getProduct("Digital"));
                        }


                    }
                    else{
                        System.out.println("Không tìm thấy sản phẩm");
                    }
                    break;
                case 3:
                    System.out.println("Nhập ID muốn xóa: ");
                    String dltId=sc.nextLine();

                    if(products.stream().filter(p->p.getId().equals(dltId)).findAny().isPresent()){
                        products.remove(dltId);
                    }
                    else{
                        System.out.println("Không tìm thấy sản phẩm");
                    }
                    break;
                case 4:
                    System.out.println("Danh sách sản phẩm");
                    products.forEach(p->p.displayInfo());
                    break;
                case 5:
                    break;
                default:
                    break;

            }
        }while(true);
    }
}
