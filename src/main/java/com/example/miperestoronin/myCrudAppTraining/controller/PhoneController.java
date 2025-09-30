package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.dto.CreatePhoneRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.PhoneNumberDto;
import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.service.PhoneService;
import com.example.miperestoronin.myCrudAppTraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneService phoneService;
    private final UserService userService; // ← поле добавлено

    // Конструктор с UserService
    public PhoneController(PhoneService phoneService, UserService userService) {
        this.phoneService = phoneService;
        this.userService = userService; // ← внедрение
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> getPhoneById(@PathVariable Long id) {
        PhoneNumber phone = phoneService.getPhoneById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        return ResponseEntity.ok(convertToDto(phone));
    }

    @PostMapping
    public ResponseEntity<PhoneNumberDto> createPhone(@Valid @RequestBody CreatePhoneRequest request) {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(request.getNumber());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            phone.setUser(user);
        }

        PhoneNumber saved = phoneService.savePhone(phone);
        return ResponseEntity.ok(convertToDto(saved));
    }

    // Метод конвертации
    private PhoneNumberDto convertToDto(PhoneNumber phone) {
        PhoneNumberDto dto = new PhoneNumberDto();
        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());
        dto.setUserId(phone.getUser() != null ? phone.getUser().getId() : null);
        return dto;
    }
}