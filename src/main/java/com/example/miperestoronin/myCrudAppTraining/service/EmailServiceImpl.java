package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.EmailRepository;
import com.example.miperestoronin.myCrudAppTraining.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private UserRepository userRepository;

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
        if (email.getUser() != null && email.getUser().getId() != null) {
            UserModel existingUser = userRepository.findById(email.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + email.getUser().getId()));
            email.setUser(existingUser);
        }
        return emailRepository.save(email);
    }
    @Override
    public EmailModel updateEmail(Long id, EmailModel emailDetails) {
        return emailRepository.findById(id).map(email -> {
            email.setAddress(emailDetails.getAddress());
            email.setUser(emailDetails.getUser());
            return emailRepository.save(email);
        }).orElseThrow(() -> new RuntimeException("Email not found with id " + id));
    }

    @Override
    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }
}