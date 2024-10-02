package com.example.possystem.service;


import com.example.possystem.entitiy.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
    ItemCategory addCategory(ItemCategory category);
    ItemCategory updateCategory(Long id, ItemCategory category);
    void deleteCategory(Long id);
    List<ItemCategory> getAllCategories();
    ItemCategory getCategoryById(Long id);
}

