package com.saqib.localezy.entity;

import com.saqib.localezy.configuration.JpaConverterJSON;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
     String name;
     String brand;
     String shortDescription;
     List<String> images;
     int price;
     double discountInPercentage;
     String completeDescription;
     @JoinColumn(name="shop_id",referencedColumnName = "id")
             @ManyToOne
     Shop shop;
     int stockQuantity;
     double rating;
    @Convert(converter = JpaConverterJSON.class)
    @Column( length = 65000)
    Map<String,Object> category;
    List<String> colors;

    boolean available;

//     List<ProductReview>? reviews;
    String sku;

    public Product(String name, String brand, String shortDescription,
                   List<String> images, int price, double discountInPercentage,
                   String completeDescription, Shop shop, int stockQuantity,
                   double rating, Map<String, Object> category,
                   List<String> colors, boolean available, String sku) {
        this.name = name;
        this.brand = brand;
        this.shortDescription = shortDescription;
        this.images = images;
        this.price = price;
        this.discountInPercentage = discountInPercentage;
        this.completeDescription = completeDescription;
        this.shop = shop;
        this.stockQuantity = stockQuantity;
        this.rating = rating;
        this.category = category;
        this.colors = colors;
        this.available = available;
        this.sku = sku;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountInPercentage() {
        return discountInPercentage;
    }

    public void setDiscountInPercentage(double discountInPercentage) {
        this.discountInPercentage = discountInPercentage;
    }

    public String getCompleteDescription() {
        return completeDescription;
    }

    public void setCompleteDescription(String completeDescription) {
        this.completeDescription = completeDescription;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Map<String, Object> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Object> category) {
        this.category = category;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
