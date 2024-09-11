package com.example.RewardProject.Beans;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class Customer {


    @Id
    private Long id;


    private String username;


    private String password;

    public Customer(Long customerId) {
    }
}

