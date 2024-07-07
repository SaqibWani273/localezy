package com.saqib.localezy.service.shop;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.entity.Product;
import com.saqib.localezy.entity.Shop;
import com.saqib.localezy.record.EmailPasswordRecord;
import com.saqib.localezy.repository.MyUserRepository;
import com.saqib.localezy.repository.ShopRepository;
import com.saqib.localezy.service.AuthServices;
import com.saqib.localezy.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private AuthServices authServices;
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<?> registerShop(Shop shop) {

        var result=authServices.preRegistrationProcess(shop.getMyUser());
        if(result instanceof String) {
            //=> error
            return ResponseEntity.badRequest().body(result);
        }
       shop.getMyUser().setRoles("SHOP");
        //save myUser with isVerified as false(by default)
        myUserRepository.save(shop.getMyUser());

        shopRepository.save(shop);


        //send email verification

        return authServices.sendEmail(shop.getMyUser(),"Click on http://localhost:8080/shop/verify-email?token=");


    }

    @Override
    public ResponseEntity<?> verifyEmail(String token) {
        return authServices.verifyEmail(token);
    }
    @Override
    public ResponseEntity<?> loginShop(EmailPasswordRecord emailPasswordRecord) {
        if (myUserRepository.findByEmail(emailPasswordRecord.email()) == null) {

            return ResponseEntity.badRequest().body("Email Not Registered");
        }
        if(!myUserRepository.findByEmail(emailPasswordRecord.email()).isEmailVerified()){
            return ResponseEntity.badRequest().body("Email not verified");
        }
        return authServices.sendTokenBack(emailPasswordRecord);
    }

    @Override
    public ResponseEntity<?> getShop(String token) {
        String email= jwtService.extractEmail(token);
        MyUser myUser= myUserRepository.findByEmail(email);
        Shop shop=shopRepository.findByMyUser(myUser);
        return ResponseEntity.ok(shop);
    }


    @Override
    public ResponseEntity<?> addProduct(Product product) {
      return   ResponseEntity.ok("Welcome to add product");
    }
}
