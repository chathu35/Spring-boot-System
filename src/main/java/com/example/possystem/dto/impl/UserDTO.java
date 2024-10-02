package com.example.possystem.dto.impl;

import com.example.possystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDto {
    private String email;
    private String password;
    private String name;
    private String companyName;
    private String role;
}
