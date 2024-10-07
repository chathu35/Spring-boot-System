package com.example.possystem.controller;

import com.example.possystem.entitiy.Cart;
import com.example.possystem.entitiy.Invoice;
import com.example.possystem.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/addItem")
    public Cart addItemToCart(@PathVariable Long cartId, @RequestParam Long itemId, @RequestParam int quantity) {
        return cartService.addItemToCart(cartId, itemId, quantity);
    }

    @PostMapping("/{cartId}/checkout")
    public Invoice checkout(@PathVariable Long cartId) {
        return cartService.checkout(cartId);
    }
}
