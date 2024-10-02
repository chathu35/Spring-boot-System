package com.example.possystem.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(schema = "systemuser")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String email;
    private String password;
    private String name;
    private String companyName;
    private String role;
}
