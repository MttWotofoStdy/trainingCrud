package com.example.miperestoronin.myCrudAppTraining.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmailRequest {
    @Email
    private String address;

    private Long userId; // ← Только ID, не объект!
}