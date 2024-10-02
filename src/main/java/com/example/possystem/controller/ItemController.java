package com.example.possystem.controller;

import com.example.possystem.entitiy.Item;
import com.example.possystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.addItem(item), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Item> editItem(@PathVariable Long id, @RequestBody Item item) {
        return new ResponseEntity<>(itemService.updateItem(id, item), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }
}
