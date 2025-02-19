package com.verano.finanzasingenieriabackend.walletsmanagement.repositories;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}