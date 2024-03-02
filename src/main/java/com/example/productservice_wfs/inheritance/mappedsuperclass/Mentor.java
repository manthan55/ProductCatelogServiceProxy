package com.example.productservice_wfs.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//@Entity(name = "msc_mentors")
@Data
public class Mentor extends BaseUsers {
    private Double rating;
}
