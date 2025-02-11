package com.verano.finanzasingenieriabackend.walletsmanagement.repositories;


import com.verano.finanzasingenieriabackend.walletsmanagement.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}