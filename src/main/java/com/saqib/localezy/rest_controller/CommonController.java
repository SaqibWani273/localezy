package com.saqib.localezy.rest_controller;

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

    @PostMapping("/me")
    public ResponseEntity<?> testCustomerAuthentication(@RequestBody String token) {
        //we donot need to check for user authentication as every request
        //is being automatically checked using authfilterservice
        String email = jwtService.extractEmail(token);
        final MyUser myUser = myUserRepository.findByEmail(email);
        Claims claims = jwtService.extractClaims(token);
        String role = claims.get("role").toString();
        Object model;
        if (role.equals("ROLE_CUSTOMER")) {
            model = customerRepository.findByMyUser(myUser);
        } else {
            //=> ROLE_SHOP
            model = shopRepository.findByMyUser(myUser);
        }
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("role", role);
        responseBody.put("model", model);
        return ResponseEntity.ok(responseBody);

    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<?> getAllCategories() {
        return commonService.getAllCategories();
    }
}
