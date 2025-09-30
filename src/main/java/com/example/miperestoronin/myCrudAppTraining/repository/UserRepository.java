package com.example.miperestoronin.myCrudAppTraining.repository;

import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}