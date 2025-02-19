package com.verano.finanzasingenieriabackend.walletsmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String cliente;

    private Integer numeroLetrasFacturas;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Letter> letters;

    public Wallet(String nombre, String cliente, Integer numeroLetrasFacturas, Bank bank, List<Letter> letters) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.numeroLetrasFacturas = numeroLetrasFacturas;
        this.bank = bank;
        this.letters = letters;
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getNumeroLetrasFacturas() {
        return numeroLetrasFacturas;
    }

    public void setNumeroLetrasFacturas(Integer numeroLetrasFacturas) {
        this.numeroLetrasFacturas = numeroLetrasFacturas;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }
}