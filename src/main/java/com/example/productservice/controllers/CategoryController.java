package com.example.productservice.controllers;

import com.example.productservice.dtos.CategoryDTO;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.dtos.ProductTitleRequestDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/v1/categories")
    public class CategoryController {

        private CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
            this.categoryService = categoryService;
        }


        @GetMapping("/prod/{name}")
        public List<ProductDTO> getProductsByCategory(@PathVariable("name") String name) throws NotFoundException {
            return this.categoryService.getProductsByACategory(name);

        }

        @GetMapping("/title")
        public List<String> productsTitleByCategoryId(@RequestBody ProductTitleRequestDTO requestDTO) throws NotFoundException {

            List<Long> categoryIds = requestDTO.getCategoryIds();
            return this.categoryService.getProductsTitle(categoryIds);

            // return null;
        }

        @GetMapping("/products")
        public List<ProductDTO> productsByCategoryId(Long id) {

            return null;
        }


        @GetMapping
        public List<CategoryDTO> getAllCategories() {
            return this.categoryService.getAllCategoriesList();
        }

        @GetMapping("{id}")
        public Category getCategoryById(@PathVariable("id") Long id) throws NotFoundException {
            return this.categoryService.getCategoryById(id);
        }


        @PostMapping
        public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
            return this.categoryService.createCategory(categoryDTO);
        }

        @DeleteMapping("{id}")
        public CategoryDTO deleteCategoryById(@PathVariable("id") Long id) throws NotFoundException {
            return this.categoryService.deleteCategoryById(id);
        }

        @PutMapping("{id}")
        public CategoryDTO updateCategoryById(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) throws NotFoundException {
            return this.categoryService.updateCategoryById(id, categoryDTO);
        }

    }
