package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.dto.CreateEmailRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.EmailDto;
import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.service.EmailService;
import com.example.miperestoronin.myCrudAppTraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    public EmailController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<EmailDto>> getAllEmails() {
        List<EmailModel> emails = emailService.getAllEmails();
        List<EmailDto> dtoList = emails.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmailDto> getEmailById(@PathVariable Long id) {
        EmailModel email = emailService.getEmailById(id)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return ResponseEntity.ok(convertToDto(email));
    }

    @PostMapping
    public ResponseEntity<EmailDto> createEmail(@Valid @RequestBody CreateEmailRequest request) {
        EmailModel email = new EmailModel();
        email.setAddress(request.getAddress());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            email.setUser(user);
        }

        EmailModel saved = emailService.createEmail(email);
        return ResponseEntity.ok(convertToDto(saved));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable Long id, @Valid @RequestBody CreateEmailRequest request) {
        EmailModel existingEmail = emailService.getEmailById(id)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        existingEmail.setAddress(request.getAddress());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingEmail.setUser(user);
        } else {
            existingEmail.setUser(null); // отвязать от пользователя
        }

        EmailModel updated = emailService.updateEmail(id, existingEmail);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }

    private EmailDto convertToDto(EmailModel email) {
        EmailDto dto = new EmailDto();
        dto.setId(email.getId());
        dto.setAddress(email.getAddress());
        dto.setUserId(email.getUser() != null ? email.getUser().getId() : null);
        return dto;
    }
}