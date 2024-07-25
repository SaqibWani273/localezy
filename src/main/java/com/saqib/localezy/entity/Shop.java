package com.saqib.localezy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity @ToString @Getter @Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;
    @Column(nullable = false)
    boolean isVerifiedByAdmin;
//    String imageUrl;
String shopPicUrl;
    @Column(unique = true,nullable = false)
    int phoneNumber;
    String address;
    String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    String ownerName;
    String ownerPicUrl;
    String pancardPicUrl;
    String ownerIdPicUrl;
    String businessLicense;
    List<String> categories;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="location_id",referencedColumnName = "id")
    LocationInfo locationInfo;

    public Shop() {
    }

    public Shop(long id, MyUser myUser, boolean isVerifiedByAdmin,
                String shopPicUrl, int phoneNumber, String address,
                String description,  String ownerName, String ownerPicUrl,
                String pancardPicUrl, String ownerIdPicUrl,
                String businessLicense, List<String> categories,
                LocationInfo locationInfo
                ) {
        this.id = id;
        this.myUser = myUser;
        this.isVerifiedByAdmin = isVerifiedByAdmin;
        this.shopPicUrl = shopPicUrl;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description = description;
        this.createDate = Date.from(Instant.now());
        this.ownerName = ownerName;
        this.ownerPicUrl = ownerPicUrl;
        this.pancardPicUrl = pancardPicUrl;
        this.ownerIdPicUrl = ownerIdPicUrl;
        this.businessLicense = businessLicense;
        this.categories = categories;
        this.locationInfo = locationInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public boolean isVerifiedByAdmin() {
        return isVerifiedByAdmin;
    }

    public void setVerifiedByAdmin(boolean verifiedByAdmin) {
        isVerifiedByAdmin = verifiedByAdmin;
    }

    public String getShopPicUrl() {
        return shopPicUrl;
    }

    public void setShopPicUrl(String shopPicUrl) {
        this.shopPicUrl = shopPicUrl;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPicUrl() {
        return ownerPicUrl;
    }

    public void setOwnerPicUrl(String ownerPicUrl) {
        this.ownerPicUrl = ownerPicUrl;
    }

    public String getPancardPicUrl() {
        return pancardPicUrl;
    }

    public void setPancardPicUrl(String pancardPicUrl) {
        this.pancardPicUrl = pancardPicUrl;
    }

    public String getOwnerIdPicUrl() {
        return ownerIdPicUrl;
    }

    public void setOwnerIdPicUrl(String ownerIdPicUrl) {
        this.ownerIdPicUrl = ownerIdPicUrl;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }
}
