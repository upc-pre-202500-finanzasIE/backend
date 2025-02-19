package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    public Bank insertBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Long id, Bank bank) {
        return bankRepository.findById(id).map(existingBank -> {
            existingBank.setNombreBanco(bank.getNombreBanco());
            existingBank.setTasaDeInteres(bank.getTasaDeInteres());
            existingBank.setIsNominal(bank.getIsNominal());
            existingBank.setIsEfectiva(bank.getIsEfectiva());
            existingBank.setCapitalizacion(bank.getCapitalizacion());
            existingBank.setDolares(bank.isDolares());
            existingBank.setSoles(bank.isSoles());
            return bankRepository.save(existingBank);
        }).orElseThrow(() -> new IllegalArgumentException("Bank not found"));
    }

    public void deleteBankById(Long id) {
        bankRepository.deleteById(id);
    }
}