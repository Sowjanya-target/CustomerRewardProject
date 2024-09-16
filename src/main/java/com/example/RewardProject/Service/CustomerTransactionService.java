package com.example.RewardProject.Service;


import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import com.example.RewardProject.Repository.CustomerPointsRepository;
import com.example.RewardProject.Repository.CustomerTransactionRepository;
import com.example.RewardProject.Utils.MonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerTransactionService {

    @Autowired
    private CustomerTransactionRepository transactionRepository;

    @Autowired
    private CustomerPointsRepository customerPointsRepository;

    //Calculate the reward point
    public void calculateAndSaveRewardPoints(Long customerId, LocalDate startDate, LocalDate endDate) {
        int totalPoints = 0;
        try {
            List<CustomerTransaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);

            //Logic to Calculate reward points
            for (CustomerTransaction transaction : transactions) {
                double amount = transaction.getAmount();
                if (amount > 100) {
                    totalPoints += (amount - 100) * 2 + 50;
                } else if (amount >= 50) {
                    totalPoints += amount - 50;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CustomerPoints customerPoints = customerPointsRepository.findByCustomerIdAndMonthAndYear(customerId, String.valueOf(startDate.getMonthValue()), startDate.getYear());
        if (customerPoints == null) {
            customerPoints = new CustomerPoints();
            customerPoints.setCustomerID(customerId);
            customerPoints.setRewardMonth(startDate.getMonthValue());
            customerPoints.setRewardYear(startDate.getYear());
        }
        customerPoints.setRewardPoints(totalPoints);
        customerPointsRepository.save(customerPoints);
    }


    // Method to get reward points for a specific customer
    public Map<String, String> getPointsByCustomerId(Long customerID) throws Exception {
        List<CustomerPoints> pointsList = customerPointsRepository.findByCustomerID(customerID);
        Map<String, String> response = new LinkedHashMap<>();
        int totalPoints = 0;

        for (CustomerPoints cp : pointsList) {
            response.put(String.valueOf(MonthUtil.getMonthName(cp.getRewardMonth())), String.valueOf(cp.getRewardPoints()));
            totalPoints += cp.getRewardPoints();

        }
        response.put("TOTAL REWARD POINTS OF THREE MONTHS", String.valueOf(totalPoints));


        return response;

    }

    //method to get all customer reward points
    public Map<String, Map<String, String>> getAllCustomerPoints() {
        // Retrieve all customers
        Map<String, Map<String, String>> allCustomerPoints = new LinkedHashMap<>();
        try {
            List<CustomerPoints> customers = customerPointsRepository.findAll();

            for (CustomerPoints customer : customers) {
                Long customerID = customer.getId();

                // Fetch points for the current customer
                List<CustomerPoints> pointsList = customerPointsRepository.findByCustomerID(customerID);

                // Prepare the response map for this customer
                Map<String, String> response = new LinkedHashMap<>();
                int totalPoints = 0;
                for (CustomerPoints cp : pointsList) {
                    response.put(MonthUtil.getMonthName(cp.getRewardMonth()), String.valueOf(cp.getRewardPoints())); // Add month and points to the map
                    totalPoints += cp.getRewardPoints(); // Accumulate total points
                }
                if (!response.isEmpty()) {
                    response.put("TOTAL REWARD POINTS OF THREE MONTHS", String.valueOf(totalPoints));
                    allCustomerPoints.put(customerID.toString(), response); // Use customer.getId().toString()
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allCustomerPoints;
    }
}
