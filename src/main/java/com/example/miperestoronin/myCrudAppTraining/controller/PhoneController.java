package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    // Получить все номера
    @GetMapping
    public List<PhoneNumber> getAllPhones() {
        return phoneService.getAllPhones();
    }

    // Получить номер по ID
    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumber> getPhoneById(@PathVariable Long id) {
        return phoneService.getPhoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Добавить новый номер
    @PostMapping
    public ResponseEntity<PhoneNumber> createPhone(@RequestBody PhoneNumber phoneNumber) {
        PhoneNumber savedPhone = phoneService.savePhone(phoneNumber);
        return ResponseEntity.ok(savedPhone);
    }

    // Обновить существующий номер
    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumber> updatePhone(
            @PathVariable Long id,
            @RequestBody PhoneNumber updatedPhone) {
        PhoneNumber phone = phoneService.updatePhone(id, updatedPhone);
        if (phone != null) {
            return ResponseEntity.ok(phone);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить номер
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        if (phoneService.deletePhone(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}