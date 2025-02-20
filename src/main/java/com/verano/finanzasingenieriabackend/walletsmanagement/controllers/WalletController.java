// WalletController.java
package com.verano.finanzasingenieriabackend.walletsmanagement.controllers;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Wallet;
import com.verano.finanzasingenieriabackend.walletsmanagement.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
@CrossOrigin(origins = "http://localhost:5173")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Long id) {
        return walletService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Wallet> insertWallet(@RequestBody Wallet wallet) {
        Wallet savedWallet = walletService.saveWallet(wallet);
        return ResponseEntity.ok(savedWallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWalletById(@PathVariable Long id) {
        walletService.deleteWalletById(id);
    }

    @PutMapping("/{id}")
    public Wallet updateWalletById(@PathVariable Long id, @RequestBody Wallet wallet) {
        return walletService.updateWalletById(id, wallet);
    }
}