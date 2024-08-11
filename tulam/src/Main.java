import ra.entity.Category;
import ra.entity.Product;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Category[] categories = new Category[100];
    public static Product[] product = new Product[100];
    public static int currentPro = 0;
    public static int currentCate = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // show ra menu
        do {
            Main main = new Main();
            System.out.println("********************* SHOP *********************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("************************************************");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    main.menuCategory(scanner);
                    break;
                }
                case 2: {
                    main.menuProduct(scanner);
                    break;
                }
                case 3: {
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3");
            }
        }
        while (true);
    }

    public void menuCategory(Scanner scanner) {
        boolean isLoop = true;
        do {
            System.out.println("---------------------------CATEGORIES MENU---------------------------\n" +
                    "\n" +
                    "1. Nhập thông tin các danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật thông tin danh mục\n" +
                    "4. Xóa danh mục\n" +
                    "5. Cập nhật trạng thái danh mục\n" +
                    "6. Thoát");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    addNewCategories(scanner);
                    break;
                }
                case 2: {
                    showCategories();
                    break;
                }
                case 3: {
                    updateCategories(scanner);
                    break;
                }
                case 4: {
                    deleteCategories(scanner);
                    break;
                }
                case 5: {
                    updateStatus(scanner);
                    break;
                }
                case 6: {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6");
            }
        }
        while (isLoop);
    }

    private void updateStatus(Scanner scanner) {
        System.out.println("Nhập id muốn thay đổi");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        int indexUD = FindIndexByid(idUpdate);
       if (indexUD != -1) {
           for (int i = 0; i < currentCate; i++) {

               categories[indexUD].inputUpdate(scanner,categories,currentCate);
           }
       }
       else {
           System.err.println("Không tồn tại id có mã"+idUpdate);
       }
    }

    private void deleteCategories(Scanner scanner) {
        System.out.println("Mời bạn nhập id cần xóa");
        int idDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = FindIndexByid(idDelete);
        if (indexDelete != -1) {
            for (int i = indexDelete; i < currentCate; i++) {
                categories[i + 1] = categories[i];
                currentCate--;
            }
        } else {
            System.err.println("Không tìm thấy index" + idDelete + "Muốn xóa");
        }

    }

    private void updateCategories(Scanner scanner) {
        System.out.println("Nhập id muốn cập nhật trạng thái");
        int idUpdateCate = Integer.parseInt(scanner.nextLine());
        int indexUpdateCate = FindIndexByid(idUpdateCate);
        if (indexUpdateCate != -1) {
            Category category = categories[indexUpdateCate];
            category.setCatalogStatus(!category.getCatalogStatus());
        }
    }

    public int FindIndexByid(int id) {
        for (int i = 0; i < currentCate; i++) {
            if (categories[i].getCategoryid() == id) {
                return i;
            }
        }
        return -1;
    }

    private void showCategories() {
        if (currentCate == 0) {
            System.err.println("Danh mục trống");
        } else {
            for (int i = 0; i < currentCate; i++) {
                categories[i].displayCategory();
            }
        }
    }

//

    private void addNewCategories(Scanner scanner) {
        System.out.println("Mời bạn nhập số lượng muốn thêm vào");
        do {
            int n = Integer.parseInt(scanner.nextLine());
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    System.out.println("Mời nhập danh mục thứ " + (i + 1));
                    categories[currentCate] = new Category();
                    categories[currentCate].inputData(scanner, categories, currentCate);
                    currentCate++;
                }
                break;


            } else {
                System.err.println("n chạy từ 1 xin vui lòng nhập lại");
            }

        } while (true);


    }

    public void menuProduct(Scanner scanner) {
        boolean isLoop = true;
        do {
            System.out.println("---------------------------PRODUCT MANAGEMENT---------------------------\n" +
                    "\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4.Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    addNewProduct(scanner);
                    break;
                }
                case 2: {
                    displayAllProduct();
                    break;
                }
                case 3: {
                    sortProdcutPrice();
                    // sắp xếp thì sử dụng thuật toán bubble sort để sắp xếp
                    break;
                }
                case 4: {
                    updateProduct(scanner);
                    break;
                }
                case 5: {
                    deleteProduct(scanner);
                    break;
                }
                case 6: {
                    // sử dụng for -> lấy name ra so sánh sử dụng contains( tìm kiếm ) xong gọi displayData() để hiển thị
                    searchProductByName(scanner);
                    break;
                }
                case 7: {
                    searchProductByPrice(scanner);
                    // nhập khoảng giá a = 100.000 ->  b = 200.000 => gọi displayData() -> để hiển thị
                    break;
                }
                case 8: {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6");
            }
        }
        while (isLoop);
    }

    private void deleteProduct(Scanner scanner) {
        while (true){
            System.out.println("Mời bạn nhập iD muốn xóa");
            String ID = scanner.nextLine();
            int indexDelete=-1;
            for (int i = 0; i < currentPro; i++) {
                if(product[i].getProductID().equals(ID)) {
                    indexDelete=i;
                    break;
                }
            }
            if(indexDelete == -1) {
                System.out.println("Không tìm thấy id muốn xóa");
            }
            else {
                for (int i = indexDelete; i < currentPro; i++) {
                    product[i]=product[i+1];


                }
                System.out.println("Xóa thành công");
                currentPro--;
                break;
            }

        }



    }

    private void updateProduct(Scanner scanner) {
        System.out.println("Nhập id muốn thay đổi");
        String idUpdateP = scanner.nextLine();
     ;
        if (  findIndexPDBYID(idUpdateP)!=null) {
            for (int i = 0; i < currentCate; i++) {

                findIndexPDBYID(idUpdateP).inputUpdate(scanner,product,currentPro,categories,currentCate);
            }
        }
        else {
            System.err.println("Không tồn tại id có mã"+idUpdateP);
        }
    }

    private void searchProductByPrice(Scanner scanner) {
        System.out.println("Nhập khoảng giá bắt đầu");
        double startPrice=Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập khoảng giá kết thúc");
        double endPrice=Double.parseDouble(scanner.nextLine());
        if (startPrice >= endPrice) {
            System.err.println("Khoảng giá "+startPrice+"phải nhỏ hơn"+endPrice);
        }else {
            boolean check = false;
            for (int i = 0; i < currentPro; i++) {
                if(product[i].getProductPrice()>=startPrice&&product[i].getProductPrice()<=endPrice){
                    check = true;
                    product[i].displayData(categories,currentCate);
                }

            }if (!check){
                System.err.println("Không tìm thấy sản phẩm");
            }
        }
    }


    private void searchProductByName(Scanner scanner) {

        System.out.println("Mời bạn nhập tên sản phẩm muốn tìm?");
        String keyword = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < currentCate; i++) {
            if(product[i].getProductName().contains(keyword)) {
                product[i].displayData(categories, currentCate);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Không tìm thấy tên sản phẩm"+keyword);
        }
    }

    private void sortProdcutPrice() {
        for (int i = 1; i < currentPro; i++) {
            for (int j = i + 1; j < currentPro; j++) {
                if (product[i].getProductPrice() < product[j].getProductPrice()) {
                    Product temp = product[i];
                    product[i] = product[j];
                    product[j] = temp;
                }
//
            }
        }
        displayAllProduct();
    }
    public static Product findIndexPDBYID(String id2) {
        for (int i = 0; i < currentPro; i++) {
            if(product[i].getProductID().equals(id2) ){
                return product[i];}
        }
        return null;
    }


    private void addNewProduct(Scanner scanner) {
        System.out.println("Nhập số sản phẩm muốn thêm vào?");
        do {if(currentCate==0){
            System.err.println("Danh sách danh mục đang trống cần khởi tạo");
            break;
        }
            int n = Integer.parseInt(scanner.nextLine());
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    System.out.println("Mã sản phẩm thứ"+(i+1));
                    product[currentPro] = new Product();
                    product[currentPro].inputData(scanner, product, currentPro, categories, currentCate);
                    currentPro++;
                }
                break;
            } else {
                System.err.println("n chạy từ 1 xin vui lòng nhập lại");
            }
        } while (true);
    }

    private void displayAllProduct() {
        if (currentPro == 0) {
            System.err.println("Danh sách trống");
            return;
        }

        for (int i = 0; i < currentPro; i++) {
            product[i].displayData(categories, currentCate);
        }
    }
}


