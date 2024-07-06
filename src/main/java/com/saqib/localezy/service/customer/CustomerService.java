package com.saqib.localezy.service.customer;

import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.record.EmailPasswordRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    //register
    ResponseEntity<?> registerCustomer(MyUser myUser);

    //login

    ResponseEntity<?> loginCustomer(EmailPasswordRecord emailPasswordRecord);

    //email verification

    ResponseEntity<?> verifyEmail(String token);

    //forgot password

    ResponseEntity<?> getCustomer(String jwtToken);



}
