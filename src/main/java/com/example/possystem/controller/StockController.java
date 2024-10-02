package com.example.possystem.controller;

import com.example.possystem.dto.impl.api.StockDto;
import com.example.possystem.entitiy.Stock;
import com.example.possystem.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/stock")
@CrossOrigin
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<StockDto> addStock(@RequestBody StockDto stock) {
        return new ResponseEntity<>(stockService.addStock(stock), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable Long id, @RequestBody StockDto stock) {
        return new ResponseEntity<>(stockService.updateStock(id, stock), HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<StockDto> getStock(@PathVariable Long id) {
        return new ResponseEntity<>(stockService.getStockByItemId(id), HttpStatus.OK);
    }
}

