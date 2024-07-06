package com.saqib.localezy.service;

import com.saqib.localezy.entity.Admin;
import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.repository.AdminRepository;
import com.saqib.localezy.repository.CustomerRepository;
import com.saqib.localezy.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
private MyUserRepository myUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
MyUser myUser=   myUserRepository.findByEmail(email);
if(myUser==null){
    throw new UsernameNotFoundException(email);
}
return User.builder()
        .username(myUser.getEmail())
        .password(myUser.getPassword())
        .roles(myUser.getRoles()).build();
    }

}