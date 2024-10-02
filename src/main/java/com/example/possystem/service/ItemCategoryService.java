package com.example.possystem.service;


import com.example.possystem.dto.impl.api.ItemCategoryDto;
import com.example.possystem.entitiy.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
    ItemCategoryDto addCategory(ItemCategoryDto category);
    ItemCategory updateCategory(Long id, ItemCategory category);
    void deleteCategory(Long id);
    List<ItemCategory> getAllCategories();
    ItemCategory getCategoryById(Long id);
}

