package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneNumberRepository phoneRepository;

    public PhoneServiceImpl(PhoneNumberRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<PhoneNumber> getAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    public Optional<PhoneNumber> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    @Override
    public PhoneNumber savePhone(PhoneNumber phone) {
        // Просто сохраняем — вся логика привязки к пользователю
        // уже обработана в контроллере через DTO
        return phoneRepository.save(phone);
    }

    @Override
    public PhoneNumber updatePhone(Long id, PhoneNumber updatedPhone) {
        if (phoneRepository.existsById(id)) {
            updatedPhone.setId(id);
            return phoneRepository.save(updatedPhone);
        }
        return null;
    }

    @Override
    public boolean deletePhone(Long id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}