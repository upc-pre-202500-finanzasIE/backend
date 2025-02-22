package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            existingBank.setNominal(bank.isNominal());
            existingBank.setEfectiva(bank.isEfectiva());
            existingBank.setCapitalizacion(bank.getCapitalizacion());
            existingBank.setDolares(bank.isDolares());
            existingBank.setSoles(bank.isSoles());
            return bankRepository.save(existingBank);
        }).orElseThrow(() -> new IllegalArgumentException("Bank not found"));
    }

    public void deleteBankById(Long id) {
        bankRepository.deleteById(id);
    }
    public List<Bank> getBankByTipoMoneda(String tipoMoneda) {
        return bankRepository.findAll().stream()
                .filter(bank -> "soles".equalsIgnoreCase(tipoMoneda) ? bank.isSoles() : bank.isDolares())
                .collect(Collectors.toList());
    }
}