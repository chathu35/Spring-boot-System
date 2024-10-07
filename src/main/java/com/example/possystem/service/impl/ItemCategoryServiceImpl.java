package com.example.possystem.service.impl;

import com.example.possystem.dto.impl.api.ItemCategoryDto;
import com.example.possystem.entitiy.ItemCategory;
import com.example.possystem.repository.ItemCategoryRepository;
import com.example.possystem.service.ItemCategoryService;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemCategoryDto addCategory(ItemCategoryDto category) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(category,ItemCategory.class)),ItemCategoryDto.class);
    }

    @Override
    public ItemCategoryDto updateCategory(Long id, ItemCategoryDto category) {
        Optional<ItemCategory> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            ItemCategory updatedCategory = existingCategory.get();
            updatedCategory.setCategoryName(category.getCategoryName());
            return modelMapper.map(categoryRepository.save(updatedCategory),ItemCategoryDto.class);
        }
        throw new RuntimeException("Category not found");
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<ItemCategoryDto> getAllCategories() {
        return modelMapper.map(categoryRepository.findAll(),List.class);
    }

    @Override
    public ItemCategoryDto getCategoryById(Long id) {
        return modelMapper.map(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"))
                ,ItemCategoryDto.class);
    }
}

