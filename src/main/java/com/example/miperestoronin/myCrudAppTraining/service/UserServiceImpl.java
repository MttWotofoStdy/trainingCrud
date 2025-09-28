package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.EmailModel;
import com.example.miperestoronin.myCrudAppTraining.entity.PhoneNumber;
import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public UserModel updateUser(Long id, UserModel userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
                      user.setAge(userDetails.getAge());

            // Обновляем телефонные номера (если нужно)
            if (userDetails.getPhoneNumbers() != null) {
                user.getPhoneNumbers().clear();
                for (PhoneNumber phone : userDetails.getPhoneNumbers()) {
                    phone.setUser(user);
                    user.getPhoneNumbers().add(phone);
                }
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

}