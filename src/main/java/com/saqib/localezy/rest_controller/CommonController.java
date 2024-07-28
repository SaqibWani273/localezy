package com.saqib.localezy.rest_controller;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.repository.CustomerRepository;
import com.saqib.localezy.repository.MyUserRepository;
import com.saqib.localezy.repository.ShopRepository;
import com.saqib.localezy.service.common.CommonService;
import com.saqib.localezy.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CommonController {
    @Autowired
    JwtService jwtService;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CommonService commonService;


    @GetMapping("/test")
    public String test() {
        return "tested successfully";
    }
    @PostMapping("/me")
    public ResponseEntity<?> testCustomerAuthentication(@RequestBody String token) {
        //we donot need to check for user authentication as every request
        //is being automatically checked using authfilterservice
        String email = jwtService.extractEmail(token);
        final Optional<MyUser> myUser = myUserRepository.findByEmail(email);
        Claims claims = jwtService.extractClaims(token);
        String role = claims.get("role").toString();
        Map<String, Object> responseBody = new HashMap<>();
        if (role.equals("ROLE_CUSTOMER")) {
            responseBody.put( "model", customerRepository.findByMyUser(myUser.get()));
        } else {
            //=> ROLE_SHOP
            responseBody.put( "model", shopRepository.findByMyUser(myUser.get()));

        }
        responseBody.put("role", role);

        //shop model
        return ResponseEntity.ok(responseBody);

    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<?> getAllCategories() {
        return commonService.getAllCategories();
    }
    @PostMapping("/email-exists")
    public ResponseEntity<?> emailExists(@RequestBody String email) {
        return commonService.emailExists(email);
    }
//    @PostMapping("/phone-exists")
//    public ResponseEntity<?> phoneExists(@RequestBody String phone) {
//        return commonService.phoneExists(phone);
//    }
    @PostMapping("/username-exists")
    public ResponseEntity<?> usernameExists(@RequestBody String username) {
        return commonService.usernameExists(username);
    }

}
