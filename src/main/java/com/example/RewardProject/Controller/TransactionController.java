package com.example.RewardProject.Controller;


import com.example.RewardProject.Response.PointResponse;
import com.example.RewardProject.Service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private CustomerTransactionService transactionService;

    @PostMapping("/calculatePoints")
    public ResponseEntity<?> calculatePoints(@RequestParam Long customerId,
                                             @RequestParam LocalDate startDate,
                                             @RequestParam LocalDate endDate) {
        transactionService.calculateAndSaveRewardPoints(customerId, startDate, endDate);
        return ResponseEntity.ok("Reward points calculated and saved successfully");
    }

    @GetMapping("/{customerId}/rewardPoints")
    public ResponseEntity<Map<String, String>> getPointsByCustomerId(@PathVariable Long customerId) {
        try {
            Map<String, String> result = transactionService.getPointsByCustomerId(customerId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/GetAllCustomerPoints")
    public ResponseEntity<Map<String, Map<String, String>>> getAllCustomerPoints() {
        try {
            Map<String, Map<String, String>> allCustomerPoints = transactionService.getAllCustomerPoints();
            return ResponseEntity.ok(allCustomerPoints);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return a 500 Internal Server Error with a meaningful message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

