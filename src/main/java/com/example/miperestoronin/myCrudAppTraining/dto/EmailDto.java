package com.example.miperestoronin.myCrudAppTraining.dto;

import lombok.Data;

@Data
public class EmailDto {
    private Long id;
    private String address;
    private Long userId; // Только ID пользователя
}