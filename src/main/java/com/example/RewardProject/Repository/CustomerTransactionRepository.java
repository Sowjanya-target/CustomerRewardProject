package com.example.RewardProject.Repository;

import com.example.RewardProject.Beans.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}
