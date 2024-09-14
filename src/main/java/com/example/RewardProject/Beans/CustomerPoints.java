package com.example.RewardProject.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="CUSTOMER_POINTS")
public class CustomerPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CUSTOMER_ID")
    private Long customerID;

    @Column(name="REWARDMONTH")
    private int rewardMonth;

    @Column(name="REWARDYEAR")
    private int rewardYear;

    @Column(name="REWARDPOINTS")
    private int rewardPoints;
}