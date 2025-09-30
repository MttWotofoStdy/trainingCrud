package com.example.miperestoronin.myCrudAppTraining.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
    private List<EmailDto> emails;
    private List<PhoneNumberDto> phoneNumbers;
}