package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import java.util.List;
import java.util.Optional;

public interface EmailService {
    List<EmailModel> getAllEmails();
    Optional<EmailModel> getEmailById(Long id);
    EmailModel createEmail(EmailModel email);
    EmailModel updateEmail(Long id, EmailModel emailDetails);
    void deleteEmail(Long id);
}