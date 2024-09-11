package com.example.RewardProject.Service;


import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private  CustomerRepository customerRepository;



    public boolean authenticate(String username, String password) {
        boolean result = false;
        try {
            Customer customer = customerRepository.findByUsername(username);
            result =  customer != null && customer.getPassword().equals(password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    // Get all customer Details
    public Customer getALL() {
        Customer customer = null;
        try {
            customer = (Customer) customerRepository.findAll();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return customer;
    }

}
