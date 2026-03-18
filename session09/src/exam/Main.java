
package exam;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Product> products = ProductData.getProducts();
        Scanner sc = new Scanner(System.in);
        FactoryProduct factory = new FactoryProduct();

        while (true) {
            System.out.println("========= MENU =========");
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. Display products");
            System.out.println("5. Exit");
            System.out.print("Nhập lựa chọn của bạn: ");

            String choiceStr = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:

                    System.out.println("1. Physical Product");
                    System.out.println("2. Digital Product");
                    System.out.print("Chọn loại sản phẩm: ");

                    String choice2Str = sc.nextLine();
                    int choice2;
                    try {
                        choice2 = Integer.parseInt(choice2Str);
                    } catch (NumberFormatException e) {
                        System.out.println("Lựa chọn không hợp lệ!");
                        break;
                    }

                    Product newProduct = null;
                    switch (choice2) {
                        case 1:
                            newProduct = factory.getProduct("Physical");
                            break;
                        case 2:
                            newProduct = factory.getProduct("Digital");
                            break;
                        default:
                            System.out.println("Loại sản phẩm không hợp lệ!");
                    }

                    if (newProduct != null) {
                        products.add(newProduct);
                        System.out.println("Đã thêm sản phẩm.");
                    }
                    break;

                case 2:

                    System.out.print("Nhập ID muốn sửa: ");
                    String updateId = sc.nextLine();

                    Product oldProduct = findById(products, updateId);
                    if (oldProduct == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                        break;
                    }


                    Product updatedProduct;
                    if (oldProduct instanceof PhysicalProduct) {
                        updatedProduct = factory.getProduct("Physical");
                    } else if (oldProduct instanceof DigitalProduct) {
                        updatedProduct = factory.getProduct("Digital");
                    } else {
                        System.out.println("Loại sản phẩm này không hỗ trợ cập nhật qua factory.");
                        break;
                    }


                    updatedProduct.setId(oldProduct.getId());

                    int index = products.indexOf(oldProduct);
                    products.set(index, updatedProduct);

                    System.out.println("Đã cập nhật sản phẩm.");
                    break;

                case 3:

                    System.out.print("Nhập ID muốn xóa: ");
                    String deleteId = sc.nextLine();

                    Product toDelete = findById(products, deleteId);
                    if (toDelete == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                    } else {
                        products.remove(toDelete);
                        System.out.println("Đã xóa sản phẩm.");
                    }
                    break;

                case 4:

                    System.out.println("Danh sách sản phẩm:");
                    if (products.isEmpty()) {
                        System.out.println("Không có sản phẩm nào.");
                    } else {
                        products.forEach(Product::displayInfo);
                    }
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

            System.out.println();
        }
    }


    private static Product findById(List<Product> products, String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
