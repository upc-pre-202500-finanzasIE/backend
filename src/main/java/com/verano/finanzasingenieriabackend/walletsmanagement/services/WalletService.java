// WalletService.java
package com.verano.finanzasingenieriabackend.walletsmanagement.services;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Bank;
import com.verano.finanzasingenieriabackend.walletsmanagement.model.Wallet;
import com.verano.finanzasingenieriabackend.walletsmanagement.model.Letter;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.BankRepository;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.WalletRepository;
import com.verano.finanzasingenieriabackend.walletsmanagement.repositories.LetterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public Wallet updateWalletBankId(Long walletId, Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        return walletRepository.findById(walletId).map(existingWallet -> {
            existingWallet.setBank(bankId);
            Wallet updatedWallet = walletRepository.save(existingWallet);

            List<Letter> relatedLetters = letterRepository.findByWalletId(walletId);
            for (Letter letter : relatedLetters) {
                if (existingWallet.getFechaDescuento() != null) {
                    long plazoDiasDescuento = ChronoUnit.DAYS.between(existingWallet.getFechaDescuento(), letter.getFechaVencimiento());
                    letter.setPlazoDiasDescuento((int) plazoDiasDescuento);

                    double tasaEfectivaPorDias = calculateTasaEfectivaDiasPlazo(letter, bankId, bank.getTasaEfectivaCalculadaConTrigger());
                    double tasaDescontadaLetra =  calcularTasaDescontada(letter, tasaEfectivaPorDias);
                    double valorTasaDescontada = calculateValorTasaDescontada(letter, tasaDescontadaLetra);
                    double tasaEfectivaDiaria = calculateTasaEfectivaDiaria(tasaEfectivaPorDias, bank.getPeriodoTasa());
                    letter.setTasaEfectivaPorDias(tasaEfectivaPorDias);
                    letter.setValorTasaDescontada(valorTasaDescontada);
                    letter.setTasaDescontada(tasaDescontadaLetra); // Set new field
                    letter.setTasaEfectivaDiaria(tasaEfectivaDiaria);
                }
                letter.setEstado("En descuento");
                letterRepository.save(letter);
            }

            return updatedWallet;
        }).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }

    private double calculateTasaEfectivaDiasPlazo(Letter letter, Long bankId, double tasaEfectivaCalculadaConTrigger) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        double periodoTasa = bank.getPeriodoTasa();

        double tasaUsar = Math.pow(tasaEfectivaCalculadaConTrigger + 1, periodoTasa) - 1;

        double result = Math.pow(1 + tasaUsar, letter.getPlazoDiasDescuento() / (double) periodoTasa) - 1;

        return result;
    }
    private double calcularTasaDescontada(Letter letter, double tasaEfectivaPorDias) {
        return tasaEfectivaPorDias/(1+tasaEfectivaPorDias);
    }
    private double calculateTasaEfectivaDiaria(double tasaEfectivaPorDias, double periodoTasa) {
        return Math.pow(1 + tasaEfectivaPorDias, 1 / periodoTasa) - 1;
    }

    private double calculateValorTasaDescontada(Letter letter, double tasaDescontadaLetra) {
        return letter.getValorNominal() * (1 - tasaDescontadaLetra);
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

    @org.springframework.transaction.annotation.Transactional
    public void updateWalletValorNeto(Long walletId, Double gastosIniciales, Double gastosFinales) {
        System.out.println("Starting updateWalletValorNeto with walletId: " + walletId + ", gastosIniciales: " + gastosIniciales + ", gastosFinales: " + gastosFinales);

        double totalValorNeto = letterRepository.findAll().stream()
                .filter(letter -> walletId.equals(letter.getWalletId()))
                .mapToDouble(Letter::getValorTasaDescontada)
                .sum();
        System.out.println("Total Valor Neto: " + totalValorNeto);

        double totalValorNominalConjunto = letterRepository.findAll().stream()
                .filter(letter -> walletId.equals(letter.getWalletId()))
                .mapToDouble(Letter::getValorNominal)
                .sum();
        System.out.println("Total Valor Nominal Conjunto: " + totalValorNominalConjunto);

        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
        if (optionalWallet.isPresent()) {
            Wallet existingWallet = optionalWallet.get();
            System.out.println("Existing Wallet found: " + existingWallet);

            existingWallet.setValorNeto(totalValorNeto);
            existingWallet.setValorNominalConjunto(totalValorNominalConjunto);
            if (gastosIniciales != null) {
                existingWallet.setValorRecibido(totalValorNeto - gastosIniciales);
                System.out.println("Updated Valor Recibido: " + existingWallet.getValorRecibido());
            }
            if (gastosFinales != null) {
                existingWallet.setValorEntregado(totalValorNominalConjunto - gastosFinales);
                System.out.println("Updated Valor Entregado: " + existingWallet.getValorEntregado());
            }
            Wallet updatedWallet = walletRepository.save(existingWallet);
            System.out.println("Updated Wallet saved: " + updatedWallet);

            // Update the status of related letters
            List<Letter> relatedLetters = letterRepository.findByWalletId(walletId);
            for (Letter letter : relatedLetters) {
                letter.setEstado("Cancelado");
                letterRepository.save(letter);
                System.out.println("Updated Letter: " + letter);
            }
        } else {
            throw new IllegalArgumentException("Wallet not found");
        }
    }
}