package com.example.possystem.service.impl;

import com.example.possystem.entitiy.*;
import com.example.possystem.service.CartService;


import com.example.possystem.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    public Cart addItemToCart(Long cartId, Long itemId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);

        if (cartOpt.isPresent() && itemOpt.isPresent()) {
            Cart cart = cartOpt.get();
            Item item = itemOpt.get();

            // Check stock availability
            Stock stock = item.getStock();
            if (stock.getQuantity() < quantity) {
                throw new RuntimeException("Not enough stock available");
            }

            // Update stock
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);

            // Add item to cart
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(quantity);
            cart.addItem(cartItem);

            return cartRepository.save(cart);
        }

        throw new RuntimeException("Cart or Item not found");
    }

    public Invoice checkout(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();

            Invoice invoice = new Invoice();
            invoice.setPurchasedItems(cart.getItems());
            invoice.setTotal(cart.getTotal());

            return invoice;
        }

        throw new RuntimeException("Cart not found");
    }
}

