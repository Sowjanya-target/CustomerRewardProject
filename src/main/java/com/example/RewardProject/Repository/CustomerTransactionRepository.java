package com.example.RewardProject.Repository;


import com.example.RewardProject.Beans.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {


    @Query("SELECT ct FROM CustomerTransaction ct where ct.customerID  =:customerId AND ct.date BETWEEN :startDate AND :endDate ")
    List<CustomerTransaction> findByCustomerIdAndDateBetween(@Param("customerId") Long customerId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}




