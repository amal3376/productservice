package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private PriceDTO price;
    private String category;
    private String description;
    private String image;
}