package com.saqib.localezy.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.saqib.localezy.configuration.JpaConverterJSON;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

@Entity

public class ProductCategory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String imageUrl;
    String description;
    boolean isTopCategory;

    @Convert(converter = JpaConverterJSON.class)
    @Column( length = 65000)
//@JdbcTypeCode(SqlTypes.JSON)
//@Column(name = "data", columnDefinition = "jsonb")
    Map<String,Object> catSpecificMustAttributes;
@Convert(converter = JpaConverterJSON.class)
        @Column(length = 65000)
//@JdbcTypeCode(SqlTypes.JSON)
//@Column(name = "data", columnDefinition = "jsonb")
    Map<String,Object> catSpecificOptionalAttributes;



    public ProductCategory(long id, String name, String imageUrl, String description, boolean isTopCategory,  Map<String, Object> catSpecificMustAttributes, Map<String, Object> catSpecificOptionalAttributes) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isTopCategory = isTopCategory;
        this.catSpecificMustAttributes = catSpecificMustAttributes;
        this.catSpecificOptionalAttributes = catSpecificOptionalAttributes;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTopCategory() {
        return isTopCategory;
    }

    public void setIsTopCategory(boolean topCategory) {
        isTopCategory = topCategory;
    }

    public Map<String, Object> getCatSpecificMustAttributes() {
        return catSpecificMustAttributes;
    }

    public void setCatSpecificMustAttributes(Map<String, Object> catSpecificMustAttributes) {
        this.catSpecificMustAttributes = catSpecificMustAttributes;
    }

    public Map<String, Object> getCatSpecificOptionalAttributes() {
        return catSpecificOptionalAttributes;
    }

    public void setCatSpecificOptionalAttributes(Map<String, Object> catSpecificOptionalAttributes) {
        this.catSpecificOptionalAttributes = catSpecificOptionalAttributes;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", isTopCategory=" + isTopCategory +
                ", catSpecificMustAttributes=" + catSpecificMustAttributes +
                ", catSpecificOptionalAttributes=" + catSpecificOptionalAttributes +
                '}';
    }
}
