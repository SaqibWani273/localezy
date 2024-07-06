package com.saqib.localezy.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
public class EmailConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @Column(unique = true,nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;


    public EmailConfirmation(String token, MyUser myUser) {
        this.token = token;
        this.createDate = Date.from(Instant.now());
        this.myUser = myUser;
    }

    public EmailConfirmation() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MyUser getMyUser() {

        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }
}
