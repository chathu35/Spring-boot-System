package com.example.possystem.controller;


import com.example.possystem.dto.impl.api.ItemCategoryDto;
import com.example.possystem.entitiy.ItemCategory;
import com.example.possystem.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService categoryService;

    // Create Category
    @PostMapping
    public ResponseEntity<ItemCategoryDto> createCategory(@RequestBody ItemCategoryDto category) {
        ItemCategoryDto newCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    // Get All Categories
    @GetMapping
    public ResponseEntity<List<ItemCategoryDto>> getAllCategories() {
        List<ItemCategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Get Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemCategoryDto> getCategoryById(@PathVariable Long id) {
        ItemCategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    // Update Category
    @PutMapping("/{id}")
    public ResponseEntity<ItemCategoryDto> updateCategory(@PathVariable Long id, @RequestBody ItemCategoryDto category) {
        ItemCategoryDto updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    // Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
