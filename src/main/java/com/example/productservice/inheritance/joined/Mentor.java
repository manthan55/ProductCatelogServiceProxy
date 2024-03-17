package com.example.productservice.inheritance.joined;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "joined_mentors")
@Data
public class Mentor extends User {
    private Double rating;
}