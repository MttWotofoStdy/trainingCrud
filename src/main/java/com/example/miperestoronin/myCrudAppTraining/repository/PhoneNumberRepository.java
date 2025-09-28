// PhoneRepository.java
package com.example.miperestoronin.myCrudAppTraining.repository;

import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}