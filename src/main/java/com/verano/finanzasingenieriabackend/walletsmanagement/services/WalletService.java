package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Wallet;
import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.BankRepository;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.WalletRepository;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.LetterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private LetterRepository letterRepository;

    @Transactional
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Transactional
    public Wallet saveWallet(Wallet wallet) {
        Set<Long> letterIds = wallet.getLetterIds();
        for (Long letterId : letterIds) {
            if (!letterRepository.existsById(letterId)) {
                throw new IllegalArgumentException("Letter with ID " + letterId + " does not exist");
            }
        }
        return walletRepository.save(wallet);
    }

    public Wallet getById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }

    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public Wallet updateWalletById(Long id, Wallet wallet) {
        return walletRepository.findById(id).map(existingWallet -> {
            existingWallet.setNombre(wallet.getNombre());
            existingWallet.setBank(wallet.getBank());
            existingWallet.setLetterIds(wallet.getLetterIds());
            existingWallet.setFechaDescuento(wallet.getFechaDescuento());
            existingWallet.setTipoDeCartera(wallet.getTipoDeCartera());
            existingWallet.setValorNeto(wallet.getValorNeto());
            existingWallet.setValorEntregado(wallet.getValorEntregado());
            existingWallet.setValorRecibido(wallet.getValorRecibido());
            return walletRepository.save(existingWallet);
        }).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }
}