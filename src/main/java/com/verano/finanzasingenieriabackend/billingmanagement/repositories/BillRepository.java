package com.verano.finanzasingenieriabackend.billingmanagement.repositories;


import com.verano.finanzasingenieriabackend.billingmanagement.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}