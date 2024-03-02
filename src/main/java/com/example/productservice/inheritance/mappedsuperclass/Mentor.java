package com.example.productservice.inheritance.mappedsuperclass;

import lombok.Data;

//@Entity(name = "msc_mentors")
@Data
public class Mentor extends BaseUsers {
    private Double rating;
}
