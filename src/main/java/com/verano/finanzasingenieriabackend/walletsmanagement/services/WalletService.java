package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Wallet;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}