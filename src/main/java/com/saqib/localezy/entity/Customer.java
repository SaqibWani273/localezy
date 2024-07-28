package com.saqib.localezy.entity;

import com.saqib.localezy.configuration.JpaConverterJSON;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class Customer{
    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;
    @Convert(converter = JpaConverterJSON.class)
    @Column( length = 65000)
    private List<Map<String,Object>> cartItems;

    public Customer( MyUser myUser) {
        this.myUser = myUser;
    }
    public Customer() {
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

    public List<Map<String, Object>> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Map<String, Object>> cartItems) {
        this.cartItems = cartItems;
    }
}