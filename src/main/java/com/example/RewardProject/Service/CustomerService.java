package com.example.RewardProject.Service;

import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService  {

    @Autowired
    private CustomerRepository customerRepository;


        //New Registration
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
