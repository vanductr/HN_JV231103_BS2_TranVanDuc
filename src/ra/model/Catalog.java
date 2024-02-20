package ra.model;

import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    // Constructors
    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    // Getter and Setter methods
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void inputData(Boolean isAdd) {
        Scanner scanner = new Scanner(System.in);
        if (isAdd) {
            System.out.print("Nhập Catalog ID: ");
            this.catalogId = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        }
        System.out.print("Nhập Catalog Name: ");
        this.catalogName = scanner.nextLine();
        System.out.print("Nhập Descriptions: ");
        this.descriptions = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}

