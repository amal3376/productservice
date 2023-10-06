package com.example.productservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Price")
@Table(name = "prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price extends BaseModel{

    @Column(nullable = false,unique = false,length = 10,updatable = true)
    private String currency;

    @Column(nullable = false,length = 10,updatable = true)
    private double price;
}