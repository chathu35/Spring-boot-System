package com.example.possystem.repository;

import com.example.possystem.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String username);
    boolean existsByEmail(String username);
}
