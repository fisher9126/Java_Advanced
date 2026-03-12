package exam;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ProductService service = new ProductService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.print("Nhập lựa chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    showAll();
                    break;
                case 3:
                    updateQuantityById();
                    break;
                case 4:
                    deleteOutOfStock();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số 1-5.");
            }
        }
    }

    private static void addItem() {
        List<Product> products = service.getProducts();
        try {
            System.out.print("Nhập id: ");
            String idInput = sc.nextLine();

            if (idInput.trim().isEmpty()) {
                throw new InvalidProductException("ID không được để trống");
            }

            int id = Integer.parseInt(idInput);


            for (Product p : products) {
                if (p.getId() == id) {
                    throw new InvalidProductException("ID đã tồn tại");
                }
            }

            System.out.print("Nhập tên sản phẩm: ");
            String name = sc.nextLine();

            System.out.print("Nhập giá tiền: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập danh mục: ");
            String category = sc.nextLine();

            Product product = new Product(id, name, price, quantity, category);
            service.addProduct(product);
            System.out.println("Thêm sản phẩm thành công.\n");

        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu số không hợp lệ.\n");
        }
    }


    private static void showAll() {
        List<Product> products = service.getProducts();
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.\n");
            return;
        }
        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println();
    }

    private static void updateQuantityById() {
        try {
            System.out.print("Nhập ID sản phẩm cần cập nhật số lượng: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập số lượng mới: ");
            int newQuantity = Integer.parseInt(sc.nextLine());

            boolean check = service.updateQuantity(id, newQuantity);
            if (check) {
                System.out.println("Cập nhật số lượng thành công.\n");
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID: " + id + "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu số không hợp lệ. Thao tác cập nhật thất bại.\n");
        }
    }

    private static void deleteOutOfStock() {
        service.deleteOutOfStock();
        System.out.println("Đã xóa tất cả sản phẩm có số lượng = 0.\n");
    }
}
