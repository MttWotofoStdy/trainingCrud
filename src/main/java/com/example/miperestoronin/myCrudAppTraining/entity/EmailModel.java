// Email.java
package com.example.miperestoronin.myCrudAppTraining.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "emails")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Email(message = "Invalid email format")
    private String address;

    // Много email-адресов → один пользователь (или null)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // ← ВАЖНО: nullable = true
    private UserModel user;
}