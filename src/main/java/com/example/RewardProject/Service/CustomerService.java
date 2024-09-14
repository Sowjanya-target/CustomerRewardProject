package com.example.RewardProject.Service;

import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import com.example.RewardProject.Repository.CustomerPointsRepository;
import com.example.RewardProject.Repository.CustomerRepository;
import com.example.RewardProject.Repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    private CustomerPointsRepository customerPointsRepository;


    //New Registration
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<CustomerTransaction> getAllTransaction() {
        return customerTransactionRepository.findAll();
    }

    public List<CustomerPoints> getAllRewardPoints() {
        return customerPointsRepository.findAll();
    }
}
