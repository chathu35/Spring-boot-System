package com.example.possystem.service;


import com.example.possystem.dto.impl.CartRequest;
import com.example.possystem.dto.impl.InvoiceRequest;

public interface POSService {
    void addToCart(CartRequest cartRequest);
    InvoiceRequest generateInvoice(InvoiceRequest invoiceRequest);
    void updateStock(Long itemId, int quantity);
}

