package com.saqib.localezy.rest_controller;

import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.entity.Product;
import com.saqib.localezy.entity.ProductCategory;
import com.saqib.localezy.entity.Shop;
import com.saqib.localezy.record.EmailPasswordRecord;
import com.saqib.localezy.service.customer.CustomerService;
import com.saqib.localezy.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    //register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Shop shop) {
        //should atleast contain email & password
        return shopService.registerShop(shop);
    }
    //email verification endpoint
    @RequestMapping(value="/verify-email", method= {RequestMethod.GET, RequestMethod.POST})
    //GETMethod to get the confirmation token
    //POST method to set the isVerified flag to true
    public ResponseEntity<?> verifyEmail(@RequestParam("token")String token) {
        return shopService.verifyEmail(token);
    }

    //login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody EmailPasswordRecord emailPasswordRecord) {
        //should atleast contain email and password
        return shopService.loginShop(emailPasswordRecord);
    }

    @PostMapping("/me")
    public ResponseEntity<?> testCustomerAuthentication(@RequestBody String token) {
      //we donot need to check for user authentication as every request
        //is being automatically checked using authfilterservice
        return shopService.getShop(token);
    }
    @PostMapping("add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return shopService.addProduct(product);
    }

}
