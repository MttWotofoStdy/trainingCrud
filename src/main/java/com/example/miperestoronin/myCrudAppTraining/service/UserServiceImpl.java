package com.example.miperestoronin.myCrudAppTraining.service;

import com.example.miperestoronin.myCrudAppTraining.entity.UserModel;
import com.example.miperestoronin.myCrudAppTraining.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@Service // Помечаем класс как сервисный компонент Spring
@Transactional // Управляем транзакциями автоматически
public class UserServiceImpl implements UserService {

    @Autowired // Внедряем зависимость UserRepository
    private UserRepository userRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll(); // Получаем всех пользователей из базы данных
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id); // Ищем пользователя по ID
    }

    @Override
    public UserModel createUser(UserModel user) {
        return userRepository.save(user); // Сохраняем нового пользователя
    }

    @Override
    public UserModel updateUser(Long id, UserModel userDetails) {
        // Находим пользователя по ID и обновляем его данные
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("UserModel not found with id " + id));
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Удаляем пользователя по ID
    }
}