package com.example.RewardProject.Repository;

import com.example.RewardProject.Beans.CustomerPoints;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPointsRepository extends CrudRepository<CustomerPoints, Long> {



    @Query(value = "SELECT * FROM CustomerPoints WHERE customer_id = :customerId AND month = :month AND year = :year", nativeQuery = true)
    CustomerPoints findByCustomerIdAndMonthAndYear(@Param("customerId") Long customerId, @Param("month") Integer month, @Param("year") Integer year);


    List<CustomerPoints> findByCustomerId(Long customerId);
}
