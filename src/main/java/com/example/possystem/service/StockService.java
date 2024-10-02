package com.example.possystem.service;


import com.example.possystem.entitiy.Stock;

import java.util.List;

public interface StockService {
    Stock addStock(Stock stock);
    Stock updateStock(Long itemId, Stock stock);
    Stock getStockByItemId(Long itemId);
    List<Stock> getAllStock();
}

