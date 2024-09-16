package com.example.RewardProject.Repository;

import com.example.RewardProject.Beans.CustomerPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPointsRepository extends JpaRepository<CustomerPoints, Long> {


    @Query("SELECT rp FROM CustomerPoints rp WHERE rp.customerID = :customerId AND rp.rewardMonth = :month AND rp.rewardYear = :year")
    CustomerPoints findByCustomerIdAndMonthAndYear(@Param("customerId") Long customerId, @Param("month") String month, @Param("year") int year);

    List<CustomerPoints> findByCustomerID(Long customerID);


}

