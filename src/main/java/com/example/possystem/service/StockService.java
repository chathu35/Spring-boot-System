package com.example.possystem.service;


import com.example.possystem.dto.impl.api.StockDto;
import com.example.possystem.entitiy.Stock;

import java.util.List;

public interface StockService {
    StockDto addStock(StockDto stock);
    StockDto updateStock(Long itemId, StockDto stock);
    StockDto getStockByItemId(Long itemId);
    List<StockDto> getAllStock();
}

