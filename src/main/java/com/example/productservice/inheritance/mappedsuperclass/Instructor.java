package com.example.productservice.inheritance.mappedsuperclass;

import lombok.Data;

//@Entity(name = "msc_instructors")
@Data
public class Instructor extends BaseUsers {
    private String company;
}
