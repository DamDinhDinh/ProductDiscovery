package dd.dinh.productdiscovery.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    private long id;
    private String name;
    private String imageUrl;
    private String dateAdded;
    private String dateUpdated;
    private double price;
    private String brand;
    private String code;

    public Product(long id, String name, String imageUrl, String dateAdded, String dateUpdated, double price, String brand, String code) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.price = price;
        this.brand = brand;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
