package com.saqib.localezy.service;

import com.saqib.localezy.entity.Customer;
import com.saqib.localezy.entity.EmailConfirmation;
import com.saqib.localezy.repository.CustomerRepository;
import com.saqib.localezy.repository.EmailConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;
    private EmailConfirmationRepository emailConfirmationRepository;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            JavaMailSender javaMailSender,
            EmailConfirmationRepository emailConfirmationRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.emailConfirmationRepository = emailConfirmationRepository;
    }





    @Override
    public ResponseEntity<?> registerCustomer(Customer customer) {
        //check if email exists
        if (customerRepository.findByEmail(customer.getEmail()) != null) {

            return ResponseEntity.badRequest().body("Email already exists");
        }
        //check if username exists
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        //encrypt password
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        //if client set id to any value then it should be ignored
        if(customer.getId()!=0){
            customer.setId(0);
        }
        //save customer with isVerified as false(by default)
        customerRepository.save(customer);


        //send email verification
        String randomToken = UUID.randomUUID().toString();
        EmailConfirmation emailConfirmation = new EmailConfirmation(randomToken, customer);
        emailConfirmationRepository.save(emailConfirmation);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("Email Verification for Localezy");

        simpleMailMessage.setText("Click here to verify your email: http://localhost:8080/customer/verify-email?token="
                + emailConfirmation.getToken());

        sendEmail(simpleMailMessage);
        return ResponseEntity.ok("Email verification link sent to " + customer.getEmail());


    }

    @Override
    public ResponseEntity<?> loginCustomer(Customer customer) {
        return null;
    }

    @Override
    public ResponseEntity<?> verifyEmail(String token) {
        EmailConfirmation emailConfirmation = emailConfirmationRepository.findByToken(token);
        if(emailConfirmation!=null){
            Customer customer = emailConfirmation.getCustomer();
            customer.setVerified(true);
            customerRepository.save(customer);
            return ResponseEntity.ok("Email verified successfully");
        }


        return ResponseEntity.badRequest().body("Invalid token");
    }
    @Async
    void sendEmail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }
}
