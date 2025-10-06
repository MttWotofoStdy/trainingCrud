package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.dto.CreatePhoneRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.PhoneNumberDto;
import com.example.miperestoronin.myCrudAppTraining.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> getPhoneById(@PathVariable Long id) {
        return ResponseEntity.ok(phoneService.getPhoneById(id));
    }

    @PostMapping
    public ResponseEntity<PhoneNumberDto> createPhone(@Valid @RequestBody CreatePhoneRequest request) {
        return ResponseEntity.ok(phoneService.createPhone(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PhoneNumberDto>> getAllPhones() {
        return ResponseEntity.ok(phoneService.getAllPhones());
    }
}