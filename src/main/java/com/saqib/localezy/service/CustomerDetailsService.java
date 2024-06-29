package com.saqib.localezy.service;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    //do not get confused by the name of this method, we can
    //use any parameter of the particular entity,here it is CustomerEntity
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     Customer customer=   customerRepository.findByEmail(email);
     if(customer==null){
         throw new UsernameNotFoundException(email);
     }
    return User.builder()
            .username(customer.getEmail())
            .password(customer.getPassword())
            .roles(getRolesList(customer.getRoles())).build();
    }

    private String[] getRolesList(String rolesString){
if(rolesString==null || rolesString.isEmpty()){
    return new String[]{"CUSTOMER"};
}
return rolesString.split(",");
    }
}
