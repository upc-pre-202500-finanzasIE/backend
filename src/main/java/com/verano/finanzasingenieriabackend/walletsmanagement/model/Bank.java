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

    private boolean isNominal;

    private boolean isEfectiva;

    private String capitalizacion;

    private boolean isDolares;

    private boolean isSoles;

    private String gastosIniciales;

    private String gastosFinales;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bank_wallet_ids", joinColumns = @JoinColumn(name = "bank_id"))
    @Column(name = "wallet_id")
    private List<Long> walletIds;

    public Bank(String nombreBanco, Double tasaDeInteres, boolean isNominal, boolean isEfectiva, String capitalizacion, boolean isDolares, boolean isSoles, String gastosIniciales, String gastosFinales) {
        this.nombreBanco = nombreBanco;
        this.tasaDeInteres = tasaDeInteres;
        this.isNominal = isNominal;
        this.isEfectiva = isEfectiva;
        this.capitalizacion = capitalizacion;
        this.isDolares = isDolares;
        this.isSoles = isSoles;
        this.gastosIniciales = gastosIniciales;
        this.gastosFinales = gastosFinales;
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

    public boolean isNominal() {
        return isNominal;
    }

    public void setNominal(boolean isNominal) {
        this.isNominal = isNominal;
    }

    public boolean isEfectiva() {
        return isEfectiva;
    }

    public void setEfectiva(boolean isEfectiva) {
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

    public String getGastosIniciales() {
        return gastosIniciales;
    }

    public void setGastosIniciales(String gastosIniciales) {
        this.gastosIniciales = gastosIniciales;
    }

    public String getGastosFinales() {
        return gastosFinales;
    }

    public void setGastosFinales(String gastosFinales) {
        this.gastosFinales = gastosFinales;
    }

    public List<Long> getWalletIds() {
        return walletIds;
    }

    public void setWalletIds(List<Long> walletIds) {
        this.walletIds = walletIds;
    }
}