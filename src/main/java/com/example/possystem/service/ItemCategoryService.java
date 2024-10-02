package com.example.possystem.service;


import com.example.possystem.dto.impl.api.ItemCategoryDto;
import com.example.possystem.entitiy.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
    ItemCategoryDto addCategory(ItemCategoryDto category);
    ItemCategoryDto updateCategory(Long id, ItemCategoryDto category);
    void deleteCategory(Long id);
    List<ItemCategoryDto> getAllCategories();
    ItemCategoryDto getCategoryById(Long id);
}

