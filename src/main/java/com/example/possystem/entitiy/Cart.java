package com.example.possystem.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    private double total;

    // Utility method to add an item to the cart
    public void addItem(CartItem cartItem) {
        items.add(cartItem);
        cartItem.setCart(this);
        this.total += cartItem.getQuantity() * cartItem.getItem().getPrice();
    }
}
