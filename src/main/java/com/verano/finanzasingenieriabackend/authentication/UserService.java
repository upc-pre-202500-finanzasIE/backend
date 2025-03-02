package com.verano.finanzasingenieriabackend.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User register(String login, String password) {
        if (userRepository.existsByLogin(login)) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
}