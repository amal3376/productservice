package com.example.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;  // on the basis of name will check category exist or not

}
