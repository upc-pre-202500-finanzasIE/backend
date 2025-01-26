package com.verano.finanzasingenieriabackend.billingmanagement.services;

import com.verano.finanzasingenieriabackend.billingmanagement.model.Bill;
import com.verano.finanzasingenieriabackend.billingmanagement.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}