package com.example.possystem.repository;

import com.example.possystem.entitiy.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s WHERE s.item.id = :itemId")
    Stock findByItemId(@Param("itemId") Long itemId);

}

