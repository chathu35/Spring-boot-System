package com.example.possystem.service;


import com.example.possystem.entitiy.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
    Item getItemById(Long id);
    List<Item> getAllItems();
}
