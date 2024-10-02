package com.example.possystem.dto.impl.api;

import com.example.possystem.dto.SuperDto;
import com.example.possystem.entitiy.ItemCategory;
import com.example.possystem.entitiy.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto implements SuperDto {
    private String name;
    private double price;
    private String description;
    private ItemCategory category;
    private Stock stock;
}
