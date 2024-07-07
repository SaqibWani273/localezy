package com.saqib.localezy.service.customer;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.EmailConfirmation;
import com.saqib.localezy.entity.MyUser;
import com.saqib.localezy.record.EmailPasswordRecord;
import com.saqib.localezy.repository.CustomerRepository;
import com.saqib.localezy.repository.EmailConfirmationRepository;
import com.saqib.localezy.repository.MyUserRepository;
import com.saqib.localezy.service.AuthServices;
import com.saqib.localezy.service.MyUserDetailsService;
import com.saqib.localezy.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private MyUserRepository myUserRepository;
    private PasswordEncoder passwordEncoder;
//    private EmailConfirmationRepository emailConfirmationRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private MyUserDetailsService myUserDetailsService;
    private AuthServices authServices;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
//            JavaMailSender javaMailSender,
//            EmailConfirmationRepository emailConfirmationRepository,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            MyUserDetailsService myUserDetailsService,
            MyUserRepository myUserRepository,
            AuthServices authServices
            ) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
//        this.javaMailSender = javaMailSender;
//        this.emailConfirmationRepository = emailConfirmationRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
        this.myUserRepository = myUserRepository;
        this.authServices = authServices;
    }





    @Override
    public ResponseEntity<?> registerCustomer(MyUser myUser) {

      var result=authServices.preRegistrationProcess(myUser);
        if(result instanceof String) {
            //=> error
            return ResponseEntity.badRequest().body(result);
        }
        myUser.setRoles("CUSTOMER");
        //save myUser with isVerified as false(by default)
        myUserRepository.save(myUser);
        Customer customer = new Customer(myUser);
        customerRepository.save(customer);


        //send email verification

return authServices.sendEmail(myUser,"Click on http://localhost:8080/customer/verify-email?token=");

    }

    @Override
    public ResponseEntity<?> verifyEmail(String token) {
       return authServices.verifyEmail(token);
    }



    @Override
    public ResponseEntity<?> loginCustomer(EmailPasswordRecord emailPasswordRecord) {
        if (myUserRepository.findByEmail(emailPasswordRecord.email()) == null) {

            return ResponseEntity.badRequest().body("Email Not Registered");
        }
        if(!myUserRepository.findByEmail(emailPasswordRecord.email()).isEmailVerified()){
            return ResponseEntity.badRequest().body("Email not verified");
        }
     return  authServices.sendTokenBack(emailPasswordRecord);

    }
    @Override
    public ResponseEntity<?> getCustomer(String jwtToken) {
        String email= jwtService.extractEmail(jwtToken);
        MyUser myUser= myUserRepository.findByEmail(email);
        return ResponseEntity.ok(myUser);
    }


}
