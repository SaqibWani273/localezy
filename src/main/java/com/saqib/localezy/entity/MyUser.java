package com.saqib.localezy.entity;

import jakarta.persistence.*;

@Entity
public class MyUser  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true,nullable = true)
    private String username;
    @Column(unique = true,nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    private String roles;
    private boolean isEmailVerified;

    public MyUser() {
    }

    public MyUser( String username, String password, String email, String roles) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.isEmailVerified = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
