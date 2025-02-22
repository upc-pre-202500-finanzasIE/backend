package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Letter;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private WalletService walletService;

    public List<Letter> getAllLetters() {
        return letterRepository.findAll();
    }

    public Letter getLetterById(Long id) {
        return letterRepository.findById(id).orElse(null);
    }

    public Letter insertLetter(Letter letter) {
        return letterRepository.save(letter);
    }

    public Letter updateLetter(Long id, Letter letter) {
        return letterRepository.findById(id).map(existingLetter -> {
            existingLetter.setCliente(letter.getCliente());
            existingLetter.setFechaFirma(letter.getFechaFirma());
            existingLetter.setValorNominal(letter.getValorNominal());
            existingLetter.setFechaVencimiento(letter.getFechaVencimiento());
            existingLetter.setHasPlazo(letter.isHasPlazo());
            existingLetter.setPlazo(letter.getPlazo());
            existingLetter.setWalletId(letter.getWalletId());
            existingLetter.setSoles(letter.isSoles());
            existingLetter.setDolares(letter.isDolares());
            return letterRepository.save(existingLetter);
        }).orElseThrow(() -> new IllegalArgumentException("Letter not found"));
    }

    public void deleteLetterById(Long id) {
        letterRepository.deleteById(id);
    }

    public List<Letter> getByTipoMoneda(String tipoMoneda) {
        return letterRepository.findAll().stream()
                .filter(letter -> {
                    if (letter.getWalletId() == null) {
                        return "soles".equalsIgnoreCase(tipoMoneda) ? letter.isSoles() : letter.isDolares();
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Letter> getLettersByWalletId(Long walletId) {
        return letterRepository.findAll().stream()
                .filter(letter -> walletId.equals(letter.getWalletId()))
                .collect(Collectors.toList());
    }
}