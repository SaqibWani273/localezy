package com.saqib.localezy.rest_controller;


import com.saqib.localezy.entity.Admin;
import com.saqib.localezy.entity.ProductCategory;
import com.saqib.localezy.record.AdminPasswordRecord;
import com.saqib.localezy.record.EmailPasswordRecord;
import com.saqib.localezy.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/register")
    public ResponseEntity<?> login(@RequestBody AdminPasswordRecord adminPasswordRecord) {
        //should atleast contain email and password
        return adminService.registerAdmin(adminPasswordRecord);
    }
    //email verification endpoint
    @RequestMapping(value="/verify-email", method= {RequestMethod.GET, RequestMethod.POST})
    //GETMethod to get the confirmation token
    //POST method to set the isVerified flag to true
    public ResponseEntity<?> verifyEmail(@RequestParam("token")String token) {
        return adminService.verifyEmail(token);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailPasswordRecord emailPasswordRecord) {
        //should atleast contain email and password
        return adminService.login(emailPasswordRecord);
    }
    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody ProductCategory productCategory) {
        return adminService.addCategory(productCategory);
    }
    @PostMapping("/me")
    public ResponseEntity<?> testCustomerAuthentication(@RequestBody String token) {
        //we donot need to check for user authentication as every request
        //is being automatically checked using authfilterservice
        return adminService.verifyToken(token);
    }
}
