package com.example.RewardProject.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer  customer;

    private String spendDetails;

    private Double amount;

    private LocalDate date;

}
