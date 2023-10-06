package com.example.productservice.repositories;

public interface CustomQuery {

    final String GET_PRODUCT_BY_CATEGORY_ID="select * from product where category_id= :id";
    final String GET_ALL_PRODUCT_BY_PRICE_ID="Select p from  Product p where p.price= :id";

    final String READ_ALL_BY_TITLE_AND_CURRENCY="select p from Product  p where p.price.currency= :currency and p.title= :title";
}