package com.example.RewardProject.Repository;

import com.example.RewardProject.Beans.CustomerPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPointsRepository extends JpaRepository<CustomerPoints, Long> {
    CustomerPoints findByCustomerIdAndMonthAndYear(Long customerId, Integer month, Integer year);


    List<CustomerPoints> findByCustomerId(Long customerId);
}
