package com.saqib.localezy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Entity @Getter @Setter
public class LocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String shortAddress;
    double latitude;
    double longtitude;
    String completeAddress;
//
//    public String getShortAddress() {
//        return shortAddress;
//    }
//
//    public void setShortAddress(String shortAddress) {
//        this.shortAddress = shortAddress;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getLongtitude() {
//        return longtitude;
//    }
//
//    public void setLongtitude(String longtitude) {
//        this.longtitude = longtitude;
//    }
//
//    public String getCompleteAddress() {
//        return completeAddress;
//    }
//
//    public void setCompleteAddress(String completeAddress) {
//        this.completeAddress = completeAddress;
//    }
}
