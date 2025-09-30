package com.example.miperestoronin.myCrudAppTraining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    // Много телефонов → один пользователь
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
      private UserModel user;
}