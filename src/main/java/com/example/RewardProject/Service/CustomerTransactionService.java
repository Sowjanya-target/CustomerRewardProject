package com.example.RewardProject.Service;



import com.example.RewardProject.Beans.Customer;
import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import com.example.RewardProject.Repository.CustomerPointsRepository;
import com.example.RewardProject.Repository.CustomerTransactionRepository;
import com.example.RewardProject.Response.PointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class CustomerTransactionService {

        @Autowired
        private CustomerTransactionRepository transactionRepository;

        @Autowired
        private CustomerPointsRepository pointsRepository;

        //Calculate the reward point
        public void calculateAndSaveRewardPoints( Long customerId, LocalDate startDate, LocalDate endDate ) {
            List<CustomerTransaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
            int totalPoints = 0;

            for (CustomerTransaction transaction : transactions) {
                double amount = transaction.getAmount();
                if (amount > 100) {
                    totalPoints += (amount - 100) * 2 + 50 ;
                } else if (amount >= 50) {
                    totalPoints += amount - 50;
                }
            }


            CustomerPoints customerPoints = pointsRepository.findByCustomerIdAndMonthAndYear(customerId, startDate.getMonthValue(), startDate.getYear());
            if (customerPoints == null) {
                customerPoints = new CustomerPoints();
                customerPoints.setCustomer(new Customer(customerId));
                customerPoints.setMonth(startDate.getMonthValue());
                customerPoints.setYear(startDate.getYear());
            }
            customerPoints.setPoints(totalPoints);
            pointsRepository.save(customerPoints);
        }


    public List<PointResponse> getPointsByCustomerId(Long customerId) {
        List<CustomerPoints> customerPointsList = pointsRepository.findByCustomerId(customerId);
        return customerPointsList.stream()
                .map(x -> new PointResponse(x.getPoints()))
                .collect(Collectors.toList());
    }

    public List<PointResponse> getAllCustomerPoints() {
        List<CustomerPoints> customerPointsList = pointsRepository.findAll();
        return customerPointsList.stream()
                .map(x -> new PointResponse(x.getPoints()))
                .collect(Collectors.toList());
    }



}
