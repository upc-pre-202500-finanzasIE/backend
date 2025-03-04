package com.verano.finanzasingenieriabackend.walletsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wallets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @JoinColumn(name = "bank_id")
    private Long bankId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wallet_letter_ids", joinColumns = @JoinColumn(name = "wallet_id"))
    @Column(name = "letter_id")
    private Set<Long> letterIds;

    private LocalDate fechaDescuento;

    @Column(length = 50)
    private String tipoDeCartera;

    private Double valorNeto;

    private Double valorEntregado;

    private Double valorRecibido;
    private double valorNominalConjunto;
    private Double tceaConjunta;;

    public Wallet(String nombre, Long bankId, Set<Long> letterIds, LocalDate fechaDescuento, String tipoDeCartera, Double valorNeto, Double valorEntregado, Double valorRecibido, Double valorNominalConjunto) {
        this.nombre = nombre;
        this.bankId = bankId;
        this.letterIds = letterIds;
        this.fechaDescuento = fechaDescuento;
        this.tipoDeCartera = tipoDeCartera;
        this.valorNeto = valorNeto;
        this.valorEntregado = valorEntregado;
        this.valorRecibido = valorRecibido;
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

    public Long getBank() {
        return bankId;
    }

    public void setBank(Long bankId) {
        this.bankId = bankId;
    }

    public Set<Long> getLetterIds() {
        return letterIds;
    }

    public void setLetterIds(Set<Long> letterIds) {
        this.letterIds = letterIds;
    }

    public LocalDate getFechaDescuento() {
        return fechaDescuento;
    }

    public void setFechaDescuento(LocalDate fechaDescuento) {
        this.fechaDescuento = fechaDescuento;
    }

    public String getTipoDeCartera() {
        return tipoDeCartera;
    }

    public void setTipoDeCartera(String tipoDeCartera) {
        this.tipoDeCartera = tipoDeCartera;
    }

    public Double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(Double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public Double getValorEntregado() {
        return valorEntregado;
    }

    public void setValorEntregado(Double valorEntregado) {
        this.valorEntregado = valorEntregado;
    }

    public Double getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(Double valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public void setValorNominalConjunto(double valorNominalConjunto) {
        this.valorNominalConjunto = valorNominalConjunto;
    }
    public double getValorNominalConjunto() {
        return valorNominalConjunto;
    }

    public Double getTceaConjunta() {
        return tceaConjunta;
    }

    public void setTceaConjunta(Double tceaConjunta) {
        this.tceaConjunta = tceaConjunta;
    }
}