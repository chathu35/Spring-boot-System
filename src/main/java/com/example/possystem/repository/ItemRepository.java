package com.example.possystem.repository;

import com.example.possystem.entitiy.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}

