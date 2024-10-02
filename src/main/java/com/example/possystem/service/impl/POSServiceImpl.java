package com.example.possystem.service.impl;

import com.example.possystem.dto.impl.CartRequest;
import com.example.possystem.dto.impl.InvoiceRequest;
import com.example.possystem.repository.StockRepository;
import com.example.possystem.service.POSService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class POSServiceImpl implements POSService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void addToCart(CartRequest cartRequest) {
    }

    @Override
    public InvoiceRequest generateInvoice(InvoiceRequest invoiceRequest) {
        return null;
    }

    @Override
    public void updateStock(Long itemId, int quantity) {
    }
}

