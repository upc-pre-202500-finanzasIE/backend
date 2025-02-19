package com.verano.finanzasingenieriabackend.walletsmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombreBanco;

    private Double tasaDeInteres;

    private Double isNominal;

    private Double isEfectiva;

    private String capitalizacion;

    private boolean isDolares;

    private boolean isSoles;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets;

    public Bank(String nombreBanco, Double tasaDeInteres, Double isNominal, Double isEfectiva, String capitalizacion, boolean isDolares, boolean isSoles) {
        this.nombreBanco = nombreBanco;
        this.tasaDeInteres = tasaDeInteres;
        this.isNominal = isNominal;
        this.isEfectiva = isEfectiva;
        this.capitalizacion = capitalizacion;
        this.isDolares = isDolares;
        this.isSoles = isSoles;
    }

    public Bank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Double getTasaDeInteres() {
        return tasaDeInteres;
    }

    public void setTasaDeInteres(Double tasaDeInteres) {
        this.tasaDeInteres = tasaDeInteres;
    }

    public Double getIsNominal() {
        return isNominal;
    }

    public void setIsNominal(Double isNominal) {
        this.isNominal = isNominal;
    }

    public Double getIsEfectiva() {
        return isEfectiva;
    }

    public void setIsEfectiva(Double isEfectiva) {
        this.isEfectiva = isEfectiva;
    }

    public String getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(String capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public boolean isDolares() {
        return isDolares;
    }

    public void setDolares(boolean dolares) {
        isDolares = dolares;
    }

    public boolean isSoles() {
        return isSoles;
    }

    public void setSoles(boolean soles) {
        isSoles = soles;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}