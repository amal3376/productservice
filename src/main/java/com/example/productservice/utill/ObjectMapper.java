package com.example.productservice.utill;

import com.example.productservice.dtos.CategoryDTO;
import com.example.productservice.dtos.PriceDTO;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.SelfProductRepository;
import com.example.productservice.services.SelfProductServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class ObjectMapper {


    private SelfProductServiceImpl se;
    private final SelfProductRepository selfProductRepository;
    private final PriceRepository priceRepository;

    private  final CategoryRepository categoryRepository;

    public ObjectMapper(SelfProductRepository selfProductRepository, PriceRepository priceRepository, CategoryRepository categoryRepository) {
        this.selfProductRepository = selfProductRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
    }




    public static CategoryDTO convertCategoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        System.out.println(category.getName());
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static PriceDTO convertPriceToPriceDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(price.getId());
        priceDTO.setCurrency(price.getCurrency());
        priceDTO.setPrice(price.getPrice());
        return priceDTO;
    }

    public static ProductDTO convertProductToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());

        productDTO.setCategory(product.getCategory().getName());
        productDTO.setPrice(ObjectMapper.convertPriceToPriceDTO(product.getPrice()));

        return productDTO;
    }



}
