package com.saqib.localezy.service;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.record.EmailPasswordRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    //register
    ResponseEntity<?> registerCustomer(Customer customer);

    //login

    ResponseEntity<?> loginCustomer(EmailPasswordRecord emailPasswordRecord);

    //email verification

    ResponseEntity<?> verifyEmail(String token);

    //forgot password

    //reset password



}
