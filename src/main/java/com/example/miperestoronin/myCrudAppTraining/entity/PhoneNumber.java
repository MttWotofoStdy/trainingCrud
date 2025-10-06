package com.example.miperestoronin.myCrudAppTraining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
      private UserModel user;
}