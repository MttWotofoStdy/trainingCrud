package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel createUser(UserModel user);
    UserModel updateUser(Long id, UserModel userDetails);
    void deleteUser(Long id);
}