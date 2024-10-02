package com.example.possystem.service;

import com.example.possystem.dto.UserDTO;
import com.example.possystem.entitiy.User;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
}
