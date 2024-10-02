package com.example.possystem.service.impl;

import com.example.possystem.dto.impl.api.StockDto;
import com.example.possystem.entitiy.Item;
import com.example.possystem.entitiy.Stock;
import com.example.possystem.repository.ItemRepository;
import com.example.possystem.repository.StockRepository;
import com.example.possystem.service.StockService;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public StockDto addStock(StockDto stockDto) {
        // Ensure the item exists in the database
        Item item = itemRepository.findById(stockDto.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Set the item in stock to maintain the relationship
        stockDto.setItem(item);

        // Check if the stock already exists
        Stock existingStock = stockRepository.findByItemId(stockDto.getItem().getId());
        if (existingStock != null) {
            // Update existing stock
            existingStock.setQuantity(stockDto.getQuantity());
            return modelMapper.map(stockRepository.save(existingStock),StockDto.class);
        }

        // Save the new stock
        return modelMapper.map(stockRepository.save(modelMapper.map(stockDto,Stock.class)),StockDto.class);
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

