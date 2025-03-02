package com.verano.finanzasingenieriabackend.walletsmanagement.repositories;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
}