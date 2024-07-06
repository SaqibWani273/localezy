package com.saqib.localezy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.lang.reflect.Array;
import java.util.Arrays;

@Entity
public class ProductCategory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String imageUrl;
    boolean isTopCategory;
    String[] specificAttributes;

    public ProductCategory(long id, String name, String imageUrl, boolean isTopCategory, String[] specificAttributes) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isTopCategory = isTopCategory;
        this.specificAttributes = specificAttributes;
    }

    public ProductCategory() {
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

    public boolean isTopCategory() {
        return isTopCategory;
    }

    public void setTopCategory(boolean topCategory) {
        isTopCategory = topCategory;
    }

    public String[] getSpecificAttributes() {
        return specificAttributes;
    }

    public void setSpecificAttributes(String[] specificAttributes) {
        this.specificAttributes = specificAttributes;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", isTopCategory=" + isTopCategory +
                ", specificAttributes=" + Arrays.toString(specificAttributes) +
                '}';
    }
}
