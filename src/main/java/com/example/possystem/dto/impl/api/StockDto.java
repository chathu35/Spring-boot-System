package com.example.possystem.dto.impl.api;

import com.example.possystem.dto.SuperDto;
import com.example.possystem.entitiy.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDto implements SuperDto {
    private int quantity;
    private Item item;
}
