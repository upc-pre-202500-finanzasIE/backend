package com.verano.finanzasingenieriabackend.walletsmanagement.repositories;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
}