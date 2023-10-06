package com.example.productservice.repositories;
import com.example.productservice.models.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PriceRepository extends CrudRepository<Price,Long> {

    public double getPriceByPrice(double price);

}