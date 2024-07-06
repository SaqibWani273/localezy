package com.saqib.localezy.entity;

import jakarta.persistence.*;

@Entity
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;

    public Admin(MyUser myUser) {
        this.myUser = myUser;
    }

    public Admin() {
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
}