package com.saqib.localezy.rest_controller;


import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.record.EmailPasswordRecord;
import com.saqib.localezy.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerController() {
    }

    //register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody MyUser myUser) {
        //should atleast contain email & password
        return customerService.registerCustomer(myUser);
    }
    //email verification endpoint
    @RequestMapping(value="/verify-email", method= {RequestMethod.GET, RequestMethod.POST})
    //GETMethod to get the confirmation token
    //POST method to set the isVerified flag to true
    public ResponseEntity<?> verifyEmail(@RequestParam("token")String token) {
        return customerService.verifyEmail(token);
    }

    //login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody EmailPasswordRecord emailPasswordRecord) {
        //should atleast contain email and password
        return customerService.loginCustomer(emailPasswordRecord);
    }

    @PostMapping("/me")
    public ResponseEntity<?> testCustomerAuthentication(@RequestBody String token) {
      //we donot need to check for user authentication as every request
        //is being automatically checked using authfilterservice
        return customerService.getCustomer(token);
    }

}
