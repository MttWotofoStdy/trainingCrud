package com.example.miperestoronin.myCrudAppTraining.controller;

import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @GetMapping
    public List<EmailModel> getAllEmails() {
        return emailService.getAllEmails();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmailModel> getEmailById(@PathVariable Long id) {
        return emailService.getEmailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmailModel> createEmail(@RequestBody EmailModel email) {
        EmailModel savedEmail = emailService.createEmail(email);
        return ResponseEntity.ok(savedEmail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailModel> updateEmail(
            @PathVariable Long id,
            @RequestBody EmailModel emailDetails) {
        EmailModel updated = emailService.updateEmail(id, emailDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}