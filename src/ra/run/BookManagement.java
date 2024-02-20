package ra.run;

import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static final CatalogService catalogService = new CatalogService();
    private static final ProductService productService = new ProductService();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Gọi phương thức quản lý danh mục
                    manageCatalog();
                    break;
                case 2:
                    // Gọi phương thức quản lý sản phẩm
                    manageProduct();
                    break;
                case 3:
                    System.out.println("Đã thoát.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 3);
    }

    // Phương thức quản lý danh mục
    private static void manageCatalog() {
        int choice;

        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục ");
            System.out.println("5. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCatalog();
                    break;
                case 2:
                    displayCatalogs();
                    break;
                case 3:
                    editCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (true);
    }

    // Phương thức quản lý sản phẩm
    private static void manageProduct() {
        int choice;

        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Thay đổi thông tin sản phẩm theo mã sản phẩm");
            System.out.println("7. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProducts();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    sortByPriceDescending();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    editProduct();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (true);
    }

    private static void addCatalog() {
        System.out.print("Nhập số danh mục muốn thêm: ");
        int numCatalogs = scanner.nextInt();

        for (int i = 0; i < numCatalogs; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            // Tạo đối tượng Catalog và thêm vào danh sách
            Catalog catalog = new Catalog();
            catalog.inputData(true);
            catalogService.save(catalog);
        }

        System.out.println("Đã thêm " + numCatalogs + " danh mục thành công.");
    }

    private static void displayCatalogs() {
        List<Catalog> catalogList =  catalogService.getAll();
        if (catalogList.isEmpty()) {
            System.out.println("Danh sách Catalog trống.");
            return;
        }
        System.out.println("Danh sách Category:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Catalog catalog : catalogList) {
            System.out.println(catalog);
            System.out.println("------------------------------");
        }
    }

    private static void editCatalog() {
        while (true) {
            System.out.print("Nhập Id của Catalog muốn sửa: ");
            int catalogIdInput = scanner.nextInt();
            Catalog catalogEdit = catalogService.findById(catalogIdInput);
            if (catalogEdit == null) {
                System.err.println("Id nhập vào không đúng, hãy kiểm tra lại!");
            } else {
                catalogEdit.inputData(false);
                catalogService.save(catalogEdit);
                break;
            }
        }
        System.out.println("Đã sửa thành công thông tin Catalog.");
    }

    private static void deleteCatalog() {
        while (true) {
            System.out.print("Nhập Id của Catalog muốn xoá: ");
            int catalogIdInput = scanner.nextInt();
            Catalog catalogEdit = catalogService.findById(catalogIdInput);
            if (catalogEdit == null) {
                System.err.println("Id nhập vào không đúng, hãy kiểm tra lại!");
            } else {
                catalogService.delete(catalogIdInput);
                break;
            }
        }
        System.out.println("Đã xoá thành công thông tin Catalog.");
    }

    // ============================== Các phương thức quản lí của Product ================
    private static void addProducts() {
        scanner.nextLine();
        System.out.print("Nhập số sản phẩm muốn thêm: ");
        int numCatalogs = scanner.nextInt();

        for (int i = 0; i < numCatalogs; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1));
            // Tạo đối tượng Catalog và thêm vào danh sách
            Product product = new Product();
            product.inputData(true);
            productService.save(product);
        }

        System.out.println("Đã thêm " + numCatalogs + " danh mục thành công.");
    }

    private static void displayProducts() {
        List<Product> productList =  productService.getAll();
        if (productList.isEmpty()) {
            System.out.println("Danh sách Product trống.");
            return;
        }
        System.out.println("Danh sách Product:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Product product : productList) {
            System.out.println(product);
            System.out.println("------------------------------");
        }
    }

    private static void editProduct() {
        scanner.nextLine();
        while (true) {
            System.out.print("Nhập Id của Product muốn sửa: ");
            String productIdInput = scanner.nextLine();
            Product productEdit = productService.findById(productIdInput);
            if (productEdit == null) {
                System.err.println("Id nhập vào không đúng, hãy kiểm tra lại!");
            } else {
                productEdit.inputData(false);
                productService.save(productEdit);
                break;
            }
        }
        System.out.println("Đã sửa thành công thông tin Product.");
    }

    private static void deleteProduct() {
        scanner.nextLine();
        while (true) {
            System.out.print("Nhập Id của Product muốn xoá: ");
            String productIdInput = scanner.nextLine();
            Product productEdit = productService.findById(productIdInput);
            if (productEdit == null) {
                System.err.println("Id nhập vào không đúng, hãy kiểm tra lại!");
            } else {
                productService.delete(productIdInput);
                break;
            }
        }
        System.out.println("Đã xoá thành công thông tin Product.");
    }

    private static void sortByPriceDescending() {
        List<Product> productList = productService.getAll();
        // Sử dụng Comparator để so sánh giá của các sản phẩm
        Comparator<Product> priceComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                // So sánh giá của hai sản phẩm
                return Double.compare(p2.getProductPrice(), p1.getProductPrice());
                // Đảo ngược vị trí p1 và p2 để sắp xếp giảm dần
            }
        };

        // Sắp xếp danh sách sản phẩm dựa trên Comparator
        Collections.sort(productList, priceComparator);

        // Hiển thị danh sách sản phẩm đã sắp xếp
        System.out.println("Danh sách sản phẩm sau khi sắp xếp theo giá giảm dần:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void searchProductByName() {
        scanner.nextLine();
        List<Product> products = productService.getAll();
        System.out.print("Nhập tên sản phẩm muốn tìm kiếm: ");
        String name = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm sản phẩm theo tên '" + name + "':");
        boolean found = false;
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm nào có tên là '" + name + "'.");
        }
    }
}

