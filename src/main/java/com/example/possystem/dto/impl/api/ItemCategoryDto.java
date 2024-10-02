package com.example.possystem.dto.impl.api;

import com.example.possystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCategoryDto implements SuperDto {
    private String categoryName;
}
