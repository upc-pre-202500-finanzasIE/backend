package com.verano.finanzasingenieriabackend.billingmanagement.controllers;

import com.verano.finanzasingenieriabackend.billingmanagement.model.Bill;
import com.verano.finanzasingenieriabackend.billingmanagement.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }
}