package com.verano.finanzasingenieriabackend.walletsmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "letters")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String cliente;

    private LocalDate fechaFirma;

    private Double valorNominal;

    private LocalDate fechaVencimiento;

    private boolean hasPlazo;

    private int plazo;

    private boolean isSoles;

    private boolean isDolares;

    @Column(name = "wallet_id") // Ahora es solo un atributo normal
    private Long walletId;

    public Letter(String cliente, LocalDate fechaFirma, Double valorNominal, LocalDate fechaVencimiento, boolean hasPlazo, int plazo, Long walletId, boolean isSoles, boolean isDolares) {
        this.cliente = cliente;
        this.fechaFirma = fechaFirma;
        this.valorNominal = valorNominal;
        this.fechaVencimiento = fechaVencimiento;
        this.hasPlazo = hasPlazo;
        this.plazo = plazo;
        this.walletId = walletId;
        this.isSoles = isSoles;
        this.isDolares = isDolares;
    }

    public Letter(String cliente, LocalDate fechaFirma, Double valorNominal, LocalDate fechaVencimiento, boolean hasPlazo, int plazo, boolean isSoles, boolean isDolares) {
        this.cliente = cliente;
        this.fechaFirma = fechaFirma;
        this.valorNominal = valorNominal;
        this.fechaVencimiento = fechaVencimiento;
        this.hasPlazo = hasPlazo;
        this.plazo = plazo;
        this.isSoles = isSoles;
        this.isDolares = isDolares;
    }

    public Letter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(LocalDate fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public Double getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(Double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isHasPlazo() {
        return hasPlazo;
    }

    public void setHasPlazo(boolean hasPlazo) {
        this.hasPlazo = hasPlazo;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public boolean isSoles() {
        return isSoles;
    }

    public void setSoles(boolean isSoles) {
        this.isSoles = isSoles;
    }

    public boolean isDolares() {
        return isDolares;
    }

    public void setDolares(boolean isDolares) {
        this.isDolares = isDolares;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}
