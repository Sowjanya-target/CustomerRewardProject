package com.example.RewardProject.Controller;



import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.CustomException.CustomConstraintViolationException;
import com.example.RewardProject.RequestObject.LoginRequest;
import com.example.RewardProject.Service.AuthService;
import com.example.RewardProject.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/auth")
public class CustomerController {


    @Autowired
    private AuthService authService;


    @Autowired
    private CustomerService customerService;


    @GetMapping("/getall")
    private Customer getAll() {
        return authService.getALL();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            customerService.saveCustomer(customer);
        } catch (CustomConstraintViolationException ex) {
            throw new CustomConstraintViolationException("Constraint violation", ex);
        }
        return ResponseEntity.ok("Customer registered successfully");
    }
}