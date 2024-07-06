package com.saqib.localezy.repository;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByEmail(String email);
    MyUser findByUsername(String username);
}
