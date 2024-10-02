package com.example.possystem.service.impl;

import com.example.possystem.entitiy.Item;
import com.example.possystem.entitiy.Stock;
import com.example.possystem.repository.ItemRepository;
import com.example.possystem.repository.StockRepository;
import com.example.possystem.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Stock addStock(Stock stock) {
        // Ensure the item exists in the database
        Item item = itemRepository.findById(stock.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Set the item in stock to maintain the relationship
        stock.setItem(item);

        // Check if the stock already exists
        Stock existingStock = stockRepository.findByItemId(stock.getItem().getId());
        if (existingStock != null) {
            // Update existing stock
            existingStock.setQuantity(stock.getQuantity());
            return stockRepository.save(existingStock);
        }

        // Save the new stock
        return stockRepository.save(stock);
    }




    @Override
    public Stock updateStock(Long itemId, Stock stock) {
        Optional<Stock> existingStock = stockRepository.findById(itemId);
        if (existingStock.isPresent()) {
            Stock updatedStock = existingStock.get();
            updatedStock.setQuantity(stock.getQuantity());
            return stockRepository.save(updatedStock);
        }
        throw new RuntimeException("Stock not found");
    }

    @Override
    public Stock getStockByItemId(Long itemId) {
        return stockRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }
}

