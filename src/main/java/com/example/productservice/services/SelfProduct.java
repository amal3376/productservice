package com.example.productservice.services;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface SelfProduct {

    public List<ProductDTO> getAllProducts();

    public ProductDTO getById(Long id) throws NotFoundException;

    public ProductDTO createProduct(ProductDTO productDTO);

    public ProductDTO deleteProductById(Long id) throws NotFoundException;

    public ProductDTO updateProductById(Long id, ProductDTO productDTO) throws NotFoundException;

}
