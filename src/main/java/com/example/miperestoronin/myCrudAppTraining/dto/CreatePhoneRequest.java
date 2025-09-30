package com.example.miperestoronin.myCrudAppTraining.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePhoneRequest {
    @NotBlank
    private String number;

    private Long userId;
}