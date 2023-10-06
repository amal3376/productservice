package com.example.productservice.models;


import jakarta.persistence.*;
import lombok.*;

@Entity(name="Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private Price price;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id")
    private Category category;

}