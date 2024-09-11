package com.example.RewardProject.Controller;


import com.example.RewardProject.Response.PointResponse;
import com.example.RewardProject.Service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/{customerId}")
    public List<PointResponse> getPointsByCustomerId(@PathVariable Long customerId) {
        return transactionService.getPointsByCustomerId(customerId);
    }

    @GetMapping("/GetAllCustomerPoints")
    private List<PointResponse> getAllCustomerPoints() {
        return transactionService.getAllCustomerPoints();

    }

}

