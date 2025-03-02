package com.verano.finanzasingenieriabackend.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        Optional<User> user = userService.login(login, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getToken());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String login, @RequestParam String password) {
        try {
            User user = userService.register(login, password);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}