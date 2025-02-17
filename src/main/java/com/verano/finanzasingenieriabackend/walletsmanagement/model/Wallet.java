package com.verano.finanzasingenieriabackend.walletsmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String cliente;

    @Column(nullable = false)
    private Integer numeroLetrasFacturas;

    @Column(columnDefinition = "LONGTEXT")
    private String letras;

    public Wallet(String nombre, String cliente, Integer numeroLetrasFacturas, String letras) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.numeroLetrasFacturas = numeroLetrasFacturas;
        this.letras = letras;
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
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

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }
}