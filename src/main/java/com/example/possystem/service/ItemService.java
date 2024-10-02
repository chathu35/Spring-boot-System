package com.example.possystem.service;


import com.example.possystem.dto.impl.api.ItemDto;
import com.example.possystem.entitiy.Item;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDto);
    ItemDto updateItem(Long id, ItemDto itemDto);
    void deleteItem(Long id);
    ItemDto getItemById(Long id);
    List<ItemDto> getAllItems();
}
