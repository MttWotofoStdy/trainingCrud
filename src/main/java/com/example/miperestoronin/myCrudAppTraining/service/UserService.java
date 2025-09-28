package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAllUsers();
    UserModel createUser(UserModel user);
    Optional<UserModel> getUserById(Long id);
    void deleteUser(Long id);
    UserModel updateUser(Long id, UserModel userDetails);
}