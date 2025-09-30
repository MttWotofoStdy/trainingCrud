package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.EmailRepository;
import com.example.miperestoronin.myCrudAppTraining.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<EmailModel> getAllEmails() {
        return emailRepository.findAll();
    }

    @Override
    public Optional<EmailModel> getEmailById(Long id) {
        return emailRepository.findById(id);
    }

    @Override
    public EmailModel createEmail(EmailModel email) {
        System.out.println("Получен email: " + email.getAddress());
        if (email.getUser() != null) {
            System.out.println("ID пользователя: " + email.getUser().getId());
        }
        EmailModel currEmail = emailRepository.save(email);
        return currEmail;
    }

    @Override
    public EmailModel updateEmail(Long id, EmailModel emailDetails) {
        return emailRepository.findById(id).map(email -> {
            email.setAddress(emailDetails.getAddress());
            email.setUser(emailDetails.getUser());
            return emailRepository.save(email);
        }).orElseThrow(() -> new RuntimeException("Email not found"));
    }

    @Override
    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }
}