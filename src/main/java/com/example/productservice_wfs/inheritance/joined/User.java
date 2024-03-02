package com.example.productservice_wfs.inheritance.joined;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "joined_users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
}
