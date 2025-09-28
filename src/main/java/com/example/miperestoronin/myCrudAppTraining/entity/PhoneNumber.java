// PhoneNumber.java
package com.example.miperestoronin.myCrudAppTraining.entity;

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

    // Много номеров → один пользователь (или null)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // ← nullable = true → владелец может отсутствовать
    private UserModel user;
}