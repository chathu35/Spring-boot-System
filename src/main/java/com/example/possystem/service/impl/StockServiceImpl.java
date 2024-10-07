package com.example.possystem.service.impl;

import com.example.possystem.dto.impl.api.StockDto;
import com.example.possystem.entitiy.Item;
import com.example.possystem.entitiy.Stock;
import com.example.possystem.repository.ItemRepository;
import com.example.possystem.repository.StockRepository;
import com.example.possystem.service.StockService;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public StockDto updateStock(Long itemId, StockDto stock) {
        Optional<Stock> existingStock = stockRepository.findById(itemId);
        if (existingStock.isPresent()) {
            Stock updatedStock = existingStock.get();
            updatedStock.setQuantity(stock.getQuantity());
            return modelMapper.map(stockRepository.save(updatedStock),StockDto.class);
        }
        throw new RuntimeException("Stock not found");
    }

    @Override
    public StockDto getStockByItemId(Long itemId) {
        return modelMapper.map(stockRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Stock not found")),StockDto.class);
    }

    @Override
    public List<StockDto> getAllStock() {
        return modelMapper.map(stockRepository.findAll(),List.class);
    }
}

