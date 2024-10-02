package com.example.possystem.service.impl;

import com.example.possystem.entitiy.Item;
import com.example.possystem.repository.ItemCategoryRepository;
import com.example.possystem.repository.ItemRepository;
import com.example.possystem.service.ItemService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Override
    public Item addItem(Item item) {
        // Ensure category exists and is set correctly
        item.setCategory(categoryRepository.findById(item.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        // Set bidirectional relationship between Item and Stock
        if (item.getStock() != null) {
            item.getStock().setItem(item); // Set the item in the stock object to avoid null issues
        }

        // Save the item, which will also save the stock due to CascadeType.ALL
        Item save = itemRepository.save(item);
        System.out.println(save);
        return save;
    }


    @Override
    public Item updateItem(Long id, Item item) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            Item updatedItem = existingItem.get();
            updatedItem.setName(item.getName());
            updatedItem.setPrice(item.getPrice());
            updatedItem.setDescription(item.getDescription());
            updatedItem.setCategory(item.getCategory());
            return itemRepository.save(updatedItem);
        }
        throw new RuntimeException("Item not found");
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}

