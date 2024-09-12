package com.example.RewardProject.Repository;

import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction, Long> {


    @Query("SELECT cp FROM CustomerTransaction cp WHERE cp.customer.id = :customerId AND cp.date BETWEEN :startDate AND :endDate")
    List<CustomerTransaction> findByCustomerIdAndDateBetween(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}




