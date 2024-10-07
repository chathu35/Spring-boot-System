package com.example.possystem.service;

import com.example.possystem.entitiy.Cart;
import com.example.possystem.entitiy.Invoice;

public interface CartService {
    Cart addItemToCart(Long cartId, Long itemId, int quantity);
    Invoice checkout(Long cartId);
}
