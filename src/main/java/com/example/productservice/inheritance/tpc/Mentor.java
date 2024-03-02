package com.example.productservice.inheritance.tpc;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "tpc_mentors")
@Data
public class Mentor extends User {
    private Double rating;
}
