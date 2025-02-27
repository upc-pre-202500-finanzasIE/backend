// LetterRepository.java
package com.verano.finanzasingenieriabackend.walletsmanagement.repositories;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    List<Letter> findByWalletId(Long walletId);
}