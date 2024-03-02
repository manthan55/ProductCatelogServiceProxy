package com.example.productservice_wfs.inheritance.tpc;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "tpc_users")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
}
