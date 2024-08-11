package ra.entity;

import java.util.Scanner;

public class Category {
    private int catelogiD;
    private String catelogName;
    private String descriptions;
    private Boolean catalogStatus;

    public Category() {
    }

    public Category(int catelogiD, String catelogName, String descriptions, Boolean catalogStatus) {
        this.catelogiD = catelogiD;
        this.catelogName = catelogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCategoryid() {
        return catelogiD;
    }

    public void setCategoryid(int catelogiD) {
        this.catelogiD = catelogiD;
    }

    public String getCategoryName() {
        return catelogName;
    }

    public void setCategoryName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(Scanner sc, Category [] categories,int CurrentCate) {
        this.catelogiD=idAutoIncrement(categories,CurrentCate);
        this.catelogName=inputCatalogName(sc,categories,CurrentCate);
        this.descriptions=inputDescription(sc);
        this.catalogStatus=inputCataloStatus(sc);

    }
    public void inputUpdate(Scanner sc, Category [] categories,int CurrentCate) {
        this.catelogName=inputCatalogName(sc,categories,CurrentCate);
        this.descriptions=inputDescription(sc);
        this.catalogStatus=inputCataloStatus(sc);

    }

    public Boolean inputCataloStatus(Scanner sc) {
        System.out.println("Mời bạn nhập trạng thái");
        do{
            String status=sc.nextLine();
            if(status.equalsIgnoreCase("true")|| status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);

            }
            else {
                System.err.println("Vui lòng nhập đúng true hoặc false");;
            }
        }
        while (true);

    };

    public String inputDescription(Scanner sc) {
        System.out.println("Mòi bạn nhập mô tả??");
        do {
            String description = sc.nextLine();
            if(description.trim().equals("")) {
                System.err.println("Bạn không được để trống nhé bạn tôi");
            }
            else {
                return description;
            }



        }while (true);
    }

    public String inputCatalogName(Scanner sc, Category[] categories, int currentCate) {
        System.out.println("Mời bạn nhập tên dcm");
        do{
            String catalogName = sc.nextLine();
           if(catalogName.trim().isEmpty()){
               System.err.println("Ko để trống nhé");
           } else {
               if(catalogName.length()<=50){
                   boolean check=false;
                   for(int i=0;i<currentCate;i++){
                       if(categories[i].getCategoryName().equals(catalogName)){
                           check=true;
                           break;
                       }
                   }
                   if(check){
                       System.err.println("Đã nhập trùng sản phẩm");
                   }
                   else {
                       return catalogName;
                   }
               }
               else {
                   System.err.println("Không nhập quá 50 sản phẩm");
               }
           }

        }while (true);
    }

    public int idAutoIncrement(Category[] categories, int currentCate) {
     int MaxId=0;
     for (int i = 0; i < currentCate; i++) {
         if(categories[i].catelogiD>MaxId){
             MaxId=categories[i].catelogiD;
         }
     }
     return MaxId+1;
    }


    public void displayCategory() {// sử dụng printf cho kiểu phức tạp ko định dạng xuống dòng
        System.out.printf("[ ID: %d | [Name: %s |Desc: %s|Status: %s] \n ", this.catelogiD, this.catelogName, this.descriptions, this.catalogStatus? "Hoạt đồng": "Không hoạt đông" );

    }


    public void inputData(Scanner scanner) {
    }
}
