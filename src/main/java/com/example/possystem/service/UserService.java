package com.example.possystem.service;

import com.example.possystem.dto.impl.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
}
