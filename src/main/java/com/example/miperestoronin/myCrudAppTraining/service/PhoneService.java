package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.repository.PhoneNumberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {  // ← УБРАЛИ "interface"

    @Autowired
    private PhoneNumberRepository phoneRepository;

    public List<PhoneNumber> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Optional<PhoneNumber> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    public PhoneNumber savePhone(PhoneNumber phoneNumber) {
        return phoneRepository.save(phoneNumber);
    }

    public PhoneNumber updatePhone(Long id, PhoneNumber updatedPhone) {
        if (phoneRepository.existsById(id)) {
            updatedPhone.setId(id);
            return phoneRepository.save(updatedPhone);
        }
        return null;
    }

    public boolean deletePhone(Long id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}