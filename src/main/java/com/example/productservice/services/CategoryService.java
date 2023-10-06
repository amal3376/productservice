package com.example.productservice.services;


import com.example.productservice.dtos.CategoryDTO;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;

import java.util.List;

public interface CategoryService {

    public Category getCategoryById(Long id) throws NotFoundException;

    public List<String> getProductsTitle(List<Long> categoryIds) throws NotFoundException;

    public List<ProductDTO> getProductsByACategory(String categoryName) throws NotFoundException;
    public List<CategoryDTO> getAllCategoriesList();

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public CategoryDTO updateCategoryById(Long id,CategoryDTO categoryDTO) throws NotFoundException;

    public CategoryDTO deleteCategoryById(Long id) throws NotFoundException;
}