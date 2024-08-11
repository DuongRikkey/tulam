package ra.entity;


import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Product {
    private String productID;
    private String productName;
    private float productPrice;
    private String description;
    private Date created;
    private int categoryID;
    private int productStatus;

    public Product() {
    }

    public Product(String productID, String productName, float productPrice, String description, Date created, int categoryID, int productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description=description;
        this.created = created;
        this.categoryID = categoryID;
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }
    public void inputData(Scanner sc, Product[] product,int currentPro,Category[] categories,int currentCate  ){
        this.productID = inputProductId(sc,product,currentPro);
        this.productName=inputProductName(sc,product,currentPro);
        this.productPrice=inputPrice(sc);
        this.description=inputDescrip(sc);
        this.created=inputCreatDate(sc);
        this.categoryID=inputCategoryID(sc,categories,currentCate );         //CHÚ Ý

        this.productStatus=inputPStatus(sc);

    }


    private int inputPStatus(Scanner sc) {
        System.out.println("1. Đang bán");
        System.out.println("2. Hết bán");
        System.out.println("3. Không bán");
        System.out.println("Nhập lựa chọn của bạn đi");
        do {
            int status = Integer.parseInt(sc.nextLine());
            if (status >= 1 && status <=3) {
                return status-1;
            }
            else {
                System.err.println("Vui lòng nhập lại từ 1>3");
            }
        }
        while (true);

    }

    public int inputCategoryID(Scanner sc, Category[] categories, int currentCate) {
        for (int i = 0; i < currentCate; i++)
        {
            System.out.printf("[ ID: %d | Name: %s ]\n", categories[i].getCategoryid(), categories[i].getCategoryName());
        }
        // 2. lựa chọn danh mục
        System.out.println("Lựa chọn danh mục muốn thêm: ");
        do
        {
            int idChoice = Integer.parseInt(sc.nextLine());
            boolean isExist = false;
            for (int i = 0; i < currentCate; i++)
            {
                if (categories[i].getCategoryid() == idChoice)
                {
                    isExist = true;
                    break;
                }
            }
            if (isExist)
            {
                return idChoice;
            }
            else
            {
                System.err.println("Không tồn tại danh mục đó");
            }
        }
        while (true);
    }


    public Date inputCreatDate(Scanner sc) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo dd/MM/yyyy: ");
        do
        {
            String date = sc.nextLine();
            try
            {
                return sdf.parse(date);
            }
            catch (ParseException e)
            {
                System.err.println("Nhập ngày không đúng định dạng");
            }
        }
        while (true);
    }

    public String inputDescrip(Scanner sc) {
        System.out.println("Mời bạn nhập mô tả");
        do {
            String desc = sc.nextLine();
            if (desc.trim().equals("")) {
                System.err.println("Khôgn được để trống miêu tả sp");
            }
            else {
                return desc;
            }
        }
        while (true);
    }



    public float inputPrice(Scanner sc) {
        System.out.println("Mời bạn nhập giá của sản phẩm đã cho")
        ;
        do {
            String price= sc.nextLine();
            if(price.trim().equals("")){
                System.err.println("Không được để trống");
            }
            else {
                float newprice = Float.parseFloat(price);
                if(newprice > 0){
                    return newprice;
                }
                else {
                    System.err.println("giá sp phải lơn hn 0");
                }
                
            }
        }while (true);
    }

    public String inputProductName(Scanner sc, Product[] product, int currentPro) {
        System.out.println("Mời bạn nhập tên muốn nhập");
        do {
            String productName = sc.nextLine();
            if (productName.trim().equals("")) {
                System.err.println("Không được để trông tên sản phẩm");
            }
            else {
                if(productName.length()>=10||productName.length()<=50) {
                    boolean check = false;
                    for (int i = 0; i < currentPro; i++) {
                        if(product[i].getProductID().equals(productName)) {
                            check = true;
                            break;
                        }
                    }
                    if(check) {
                        System.err.println("Tên sp đã trùng lặp");
                    }
                    else {
                        return productName;
                    }
                }
                else {
                    System.err.println("Sp có ký tự từ 10-50");
                }
            }
        }
        while (true);
    }

    public String inputProductId(Scanner sc, Product[] product, int currentPro) {
        System.out.println("Nhập vào mã sp với ký tự sau(CXXX |AXXx| DXXX) với x là các chữ số");
        do {
            String id = sc.nextLine();
            if (id.matches("^[CSA]\\w{3}$")){
                boolean check = false;
                for (int i = 0; i < currentPro; i++) {
                    if(product[i].getProductID().equals(id)){
                        check = true;
                        break;
                    }

                }if (check){
                    System.err.println("Mã sp đã bị trùng");
                }
                else {
                    return id;
                }
            }
            else {
                System.err.println("Mã sp phải bắt đầu từ C S A VÀ 3 KÝ TỰ");
            }

        }while (true);
    }
    public void displayData(Category[] categories, int currentCate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.printf(
                "[ ID: %s | Name: %s | Price: %.2f | Desc: %s | Created: %s | Category: %s | Status: %s ]\n",
                this.productID,
                this.productName,
                this.productPrice,
                this.description,
                sdf.format(this.created),
                getCatelogNamebyID(categories, currentCate),
                this.productStatus == 0 ? "Đang bán" : this.productStatus == 1 ? "Hết hàng" : "Không bán"
        );

    }

    public String getCatelogNamebyID(Category[] categories, int currentCate) {
        for (int i = 0; i < currentCate; i++) {
            if (categories[i].getCategoryid()== this.categoryID){
                return categories[i].getCategoryName();
            }
        }
        return null;
    }



    public void inputUpdate(Scanner sc, Product[] product,int currentPro,Category[] categories,int currentCate  ){
        this.productName=inputProductName(sc,product,currentPro);
        this.productPrice=inputPrice(sc);
        this.description=inputDescrip(sc);
        this.created=inputCreatDate(sc);
        this.categoryID=inputCategoryID(sc,categories,currentCate );
        this.productStatus=inputPStatus(sc);

    }
}
