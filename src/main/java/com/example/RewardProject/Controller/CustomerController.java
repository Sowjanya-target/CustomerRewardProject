package com.example.RewardProject.Controller;


import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import com.example.RewardProject.RequestObject.LoginRequest;
import com.example.RewardProject.Service.AuthService;
import com.example.RewardProject.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class CustomerController {


    @Autowired
    private AuthService authService;


    @Autowired
    private CustomerService customerService;


    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    //To Fetch all Customer Details
    @GetMapping
    public List<Customer> getAllCustomers() {
        List<Customer> customers = authService.getAllCustomers();
        logger.info("Fetched customers: {}", customers);
        return customers;
    }

    //Health check
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Logged in successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    //Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            customerService.saveCustomer(customer);
            return ResponseEntity.ok("Customer registered successfully");

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Get all details for CustomerTransaction Db
    @GetMapping("/tranaction")
    public List<CustomerTransaction> tranaction() {
        return customerService.getAllTransaction();
    }

    //Get all Details for customerDeatils DB
    @GetMapping("/points")
    private List<CustomerPoints> getRewardPoints() {
        return customerService.getAllRewardPoints();
    }
}