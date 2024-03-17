package com.example.productservice.inheritance.tpc;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "tpc_instructors")
@Data
public class Instructor extends User {
    private String company;
}