package com.example.RewardProject.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "CUSTOMERTRANSACTIONS")
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerID;

    @Column(name = "SPEND_DETAILS")
    private String spendDetails;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "DATE")
    private LocalDate date;

    public CustomerTransaction(Long customerID, String spendDetails, Double amount, LocalDate date) {
        this.customerID = customerID;
        this.spendDetails = spendDetails;
        this.amount = amount;
        this.date = date;
    }

    public CustomerTransaction() {

    }
}
