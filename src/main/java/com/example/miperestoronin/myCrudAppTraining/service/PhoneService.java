package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<PhoneNumber> getAllPhones();
    Optional<PhoneNumber> getPhoneById(Long id);
    PhoneNumber savePhone(PhoneNumber phone);
    PhoneNumber updatePhone(Long id, PhoneNumber updatedPhone);
    boolean deletePhone(Long id);
}