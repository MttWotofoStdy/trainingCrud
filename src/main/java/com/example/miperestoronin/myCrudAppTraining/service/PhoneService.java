package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.dto.CreatePhoneRequest;
import com.example.miperestoronin.myCrudAppTraining.dto.PhoneNumberDto;
import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.PhoneNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PhoneService {

    private final PhoneNumberRepository phoneRepository;
    private final UserService userService;

    public PhoneService(PhoneNumberRepository phoneRepository, UserService userService) {
        this.phoneRepository = phoneRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<PhoneNumberDto> getAllPhones() {
        return phoneRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PhoneNumberDto getPhoneById(Long id) {
        PhoneNumber phone = phoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        return convertToDto(phone);
    }

    @Transactional
    public PhoneNumberDto createPhone(CreatePhoneRequest request) {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(request.getNumber());

        if (request.getUserId() != null) {
            UserModel user = userService.getUserById(request.getUserId());
            phone.setUser(user);
        }

        log.info("Создание номера телефона: {}", phone.getNumber());
        if (phone.getUser() != null) {
            log.debug("Привязка к пользователю ID: {}", phone.getUser().getId());
        }

        PhoneNumber saved = phoneRepository.save(phone);
        return convertToDto(saved);
    }

    @Transactional
    public void deletePhone(Long id) {
        if (!phoneRepository.existsById(id)) {
            throw new RuntimeException("Phone not found");
        }
        phoneRepository.deleteById(id);
    }

    private PhoneNumberDto convertToDto(PhoneNumber phone) {
        PhoneNumberDto dto = new PhoneNumberDto();
        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());
        dto.setUserId(phone.getUser() != null ? phone.getUser().getId() : null);
        return dto;
    }
}