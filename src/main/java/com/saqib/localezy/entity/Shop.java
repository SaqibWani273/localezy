package com.saqib.localezy.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@Entity @ToString
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;
    @Column(nullable = false)
    boolean isVerifiedByAdmin;
    String imageUrl;
    @Column(unique = true,nullable = false)
    int mobileNumber;
    String address;
    String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Shop() {
    }

    public Shop(String description, String address, int mobileNumber, String imageUrl, MyUser myUser) {
        this.description = description;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.imageUrl = imageUrl;
        this.myUser = myUser;
        this.isVerifiedByAdmin = false;
        this.createDate = Date.from(Instant.now());
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
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
}
