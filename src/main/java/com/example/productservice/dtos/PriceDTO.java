package com.example.productservice.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PriceDTO {
    private Long id;
    private String currency;
    private double price;


}