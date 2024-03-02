package com.example.productservice_wfs.inheritance.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "singletbl_instructors")
@Data
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
