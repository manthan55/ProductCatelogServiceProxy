package com.example.productservice_wfs.inheritance.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "singletbl_mentors")
@Data
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private Double rating;
}
