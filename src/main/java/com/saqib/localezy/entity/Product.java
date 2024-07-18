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


}
