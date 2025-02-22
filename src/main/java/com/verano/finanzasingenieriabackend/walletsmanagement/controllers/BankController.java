package com.verano.finanzasingenieriabackend.walletsmanagement.controllers;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import com.verano.finanzasingenieriabackend.walletsmanagement.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/banks")
@CrossOrigin(origins = "http://localhost:5173")
public class BankController {

    @Autowired

    private BankService bankService;

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        Bank bank = bankService.getBankById(id);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Bank insertBank(@RequestBody Bank bank) {
        return bankService.insertBank(bank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody Bank bank) {
        try {
            Bank updatedBank = bankService.updateBank(id, bank);
            return ResponseEntity.ok(updatedBank);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankById(@PathVariable Long id) {
        bankService.deleteBankById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/tipoMoneda/{tipoMoneda}")
    public List<Bank> getBankByTipoMoneda(@PathVariable String tipoMoneda) {
        return bankService.getBankByTipoMoneda(tipoMoneda);
    }
}