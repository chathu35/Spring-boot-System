package com.example.possystem.service.impl;

import com.example.possystem.entitiy.ItemCategory;
import com.example.possystem.repository.ItemCategoryRepository;
import com.example.possystem.service.ItemCategoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Override
    public ItemCategory addCategory(ItemCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public ItemCategory updateCategory(Long id, ItemCategory category) {
        Optional<ItemCategory> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            ItemCategory updatedCategory = existingCategory.get();
            updatedCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(updatedCategory);
        }
        throw new RuntimeException("Category not found");
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<ItemCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ItemCategory getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}

