package com.example.possystem.service.impl;

import com.example.possystem.dto.impl.api.ItemDto;
import com.example.possystem.entitiy.Item;
import com.example.possystem.repository.ItemCategoryRepository;
import com.example.possystem.repository.ItemRepository;
import com.example.possystem.service.ItemService;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        // Ensure category exists and is set correctly
        item.setCategory(categoryRepository.findById(item.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        // Set bidirectional relationship between Item and Stock
        if (item.getStock() != null) {
            item.getStock().setItem(item); // Set the item in the stock object to avoid null issues
        }

        // Save the item, which will also save the stock due to CascadeType.ALL
        Item saveitem = itemRepository.save(item);
        return modelMapper.map(saveitem, ItemDto.class);
    }


    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            Item updatedItem = existingItem.get();
            updatedItem.setName(item.getName());
            updatedItem.setPrice(item.getPrice());
            updatedItem.setDescription(item.getDescription());
            updatedItem.setCategory(item.getCategory());
            return modelMapper.map(itemRepository.save(updatedItem), ItemDto.class);
        }
        throw new RuntimeException("Item not found");
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ItemDto getItemById(Long id) {
        return modelMapper.map(itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found")),
                ItemDto.class);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return modelMapper.map(itemRepository.findAll(),List.class);
    }
}

