package com.verano.finanzasingenieriabackend.billingmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private Double money;

    public Bill(Double money) {
        this.money = money;
    }

    public Bill() {

    }
}