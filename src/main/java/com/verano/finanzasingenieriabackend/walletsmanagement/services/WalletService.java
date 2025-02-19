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

    public Wallet getById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public Wallet updateWalletById(Long id, Wallet wallet) {
        return walletRepository.findById(id).map(existingWallet -> {
            existingWallet
                    .setNombre(wallet.getNombre());
            existingWallet.setCliente(wallet.getCliente());
            existingWallet.setNumeroLetrasFacturas(wallet.getNumeroLetrasFacturas());
            return walletRepository.save(existingWallet);
        }).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }
}