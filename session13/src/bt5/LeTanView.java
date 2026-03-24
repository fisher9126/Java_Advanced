// LeTanView.java
package bt5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeTanView {

    private final BenhNhanController controller;
    private final Scanner scanner;

    public LeTanView() {
        this.controller = new BenhNhanController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    controller.xemGiuongTrong();
                    break;
                case 2:
                    tiepNhanBenhNhanFlow();
                    break;
                case 3:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void printMenu() {
        System.out.println("========== MENU LỄ TÂN ==========");
        System.out.println("1. Xem tình trạng giường bệnh");
        System.out.println("2. Tiếp nhận bệnh nhân nội trú");
        System.out.println("3. Thoát");
        System.out.println("=================================");
    }

    private void tiepNhanBenhNhanFlow() {
        System.out.println("=== Tiếp nhận bệnh nhân ===");
        System.out.print("Nhập tên bệnh nhân: ");
        String ten = scanner.nextLine().trim();
        if (ten.isEmpty()) {
            System.out.println("Tên không được để trống.");
            return;
        }

        int tuoi = readInt("Nhập tuổi: ");
        if (tuoi <= 0 || tuoi > 120) {
            System.out.println("Tuổi không hợp lệ.");
            return;
        }

        System.out.print("Nhập mã giường muốn chọn: ");
        String maGiuong = scanner.nextLine().trim();
        if (maGiuong.isEmpty()) {
            System.out.println("Mã giường không được để trống.");
            return;
        }

        double soTienTamUng = readDouble("Nhập số tiền tạm ứng: ");
        if (soTienTamUng <= 0) {
            System.out.println("Số tiền tạm ứng phải > 0.");
            return;
        }

        try {
            controller.tiepNhanBenhNhan(ten, tuoi, maGiuong, soTienTamUng);
            System.out.println("Tiếp nhận bệnh nhân thành công.");
        } catch (Exception e) {
            // Thông báo thân thiện (message đã được controller chuẩn bị)
            System.out.println("Tiếp nhận thất bại: " + e.getMessage());
        }
    }

    // Validate input: bẫy trường hợp nhập "Năm trăm" → không crash
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số (ví dụ: 500 hoặc 500.0).");
            }
        }
    }
}
