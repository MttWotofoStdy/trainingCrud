package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.dto.CreateEmailRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.EmailDto;
import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final UserService userService;

    public EmailService(EmailRepository emailRepository, UserService userService) {
        this.emailRepository = emailRepository;
        this.userService = userService;
    }
    @Transactional
    public List<EmailDto> getAllEmails() {
        return emailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public EmailDto getEmailById(Long id) {
        EmailModel email = emailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return convertToDto(email);
    }

    @Transactional
    public EmailDto createEmail(CreateEmailRequest request) {
        EmailModel email = new EmailModel();
        email.setAddress(request.getAddress());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId());
            email.setUser(user);
        }

        log.info("Получен email: {}", email.getAddress());
        if (email.getUser() != null) {
            log.info("ID пользователя: {}", email.getUser().getId());
        }

        EmailModel saved = emailRepository.save(email);
        return convertToDto(saved);
    }
    @Transactional
    public EmailDto updateEmail(Long id, CreateEmailRequest request) {
        EmailModel existingEmail = emailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        existingEmail.setAddress(request.getAddress());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId());
            existingEmail.setUser(user);
        } else {
            existingEmail.setUser(null);
        }

        EmailModel updated = emailRepository.save(existingEmail);
        return convertToDto(updated);
    }
    @Transactional
    public void deleteEmail(Long id) {
        if (!emailRepository.existsById(id)) {
            throw new RuntimeException("Email not found");
        }
        emailRepository.deleteById(id);
    }

    private EmailDto convertToDto(EmailModel email) {
        EmailDto dto = new EmailDto();
        dto.setId(email.getId());
        dto.setAddress(email.getAddress());
        dto.setUserId(email.getUser() != null ? email.getUser().getId() : null);
        return dto;
    }
}