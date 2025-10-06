package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.dto.CreateEmailRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.EmailDto;
import com.example.miperestoronin.myCrudAppTraining.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<EmailDto>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmailDto> getEmailById(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.getEmailById(id));
    }

    @PostMapping
    public ResponseEntity<EmailDto> createEmail(@Valid @RequestBody CreateEmailRequest request) {
        return ResponseEntity.ok(emailService.createEmail(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable Long id, @Valid @RequestBody CreateEmailRequest request) {
        return ResponseEntity.ok(emailService.updateEmail(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}