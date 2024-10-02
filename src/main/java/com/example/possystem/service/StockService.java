package com.example.possystem.service;


import com.example.possystem.dto.impl.api.StockDto;
import com.example.possystem.entitiy.Stock;

import java.util.List;

public interface StockService {
    StockDto addStock(StockDto stock);
    Stock updateStock(Long itemId, Stock stock);
    Stock getStockByItemId(Long itemId);
    List<Stock> getAllStock();
}

