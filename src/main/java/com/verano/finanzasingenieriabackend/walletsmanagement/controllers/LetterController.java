package com.verano.finanzasingenieriabackend.walletsmanagement.controllers;

import com.verano.finanzasingenieriabackend.walletsmanagement.model.Letter;
import com.verano.finanzasingenieriabackend.walletsmanagement.services.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/letters")
@CrossOrigin(origins = "http://localhost:5173")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @GetMapping
    public List<Letter> getAllLetters() {
        return letterService.getAllLetters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Letter> getLetterById(@PathVariable Long id) {
        Letter letter = letterService.getLetterById(id);
        if (letter != null) {
            return ResponseEntity.ok(letter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Letter insertLetter(@RequestBody Letter letter) {
        System.out.println("Recibiendo datos en insertLetter:");
        System.out.println("Cliente: " + letter.getCliente());
        System.out.println("Fecha Firma: " + letter.getFechaFirma());
        System.out.println("Fecha Vencimiento: " + letter.getFechaVencimiento());
        System.out.println("Tiene Plazo: " + letter.isHasPlazo());
        System.out.println("Plazo: " + letter.getPlazo());
        System.out.println("Valor Nominal: " + letter.getValorNominal());
        System.out.println("Soles: " + letter.isSoles());
        System.out.println("Dólares: " + letter.isDolares());

        return letterService.insertLetter(letter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Letter> updateLetter(@PathVariable Long id, @RequestBody Letter letter) {
        try {
            Letter updatedLetter = letterService.updateLetter(id, letter);
            return ResponseEntity.ok(updatedLetter);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLetterById(@PathVariable Long id) {
        letterService.deleteLetterById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipoMoneda/{tipoMoneda}")
    public List<Letter> getByTipoMoneda(@PathVariable String tipoMoneda) {
        return letterService.getByTipoMoneda(tipoMoneda);
    }

    @GetMapping("/wallet/{walletId}")
    public List<Letter> getLettersByWalletId(@PathVariable Long walletId) {
        return letterService.getLettersByWalletId(walletId);
    }
}