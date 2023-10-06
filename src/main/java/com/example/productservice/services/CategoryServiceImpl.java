package com.example.productservice.services;

import com.example.productservice.dtos.CategoryDTO;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.SelfProductRepository;
import com.example.productservice.utill.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private SelfProductRepository selfProductRepository;



    public CategoryServiceImpl(CategoryRepository categoryRepository,SelfProductRepository selfProductRepository) {
        this.categoryRepository = categoryRepository;
        this.selfProductRepository=selfProductRepository;
    }



    @Override
    public List<ProductDTO> getProductsByACategory(String name) throws NotFoundException {
        // only one Category object Optional<Category>
        Optional<Category> categoriesByType = this.categoryRepository.getCategoryByName(name);


        if (categoriesByType.isEmpty()) {
            throw new NotFoundException("Category Name not found in the database");
        }

        List<Product> productList= categoriesByType.get().getProducts();

        List<ProductDTO> productDTOS=new ArrayList<>();
        for(Product products : productList){
            productDTOS.add(ObjectMapper.convertProductToProductDto(products));
        }

        return productDTOS;
    }


    @Override
    public Category getCategoryById(Long id) throws NotFoundException {
        Optional<Category> category = this.categoryRepository.findById(id);

        if (category.isEmpty())
            throw new NotFoundException("Category does not exist with id : " + id);

        List<Product> products = category.get().getProducts();

        return category.get();

    }

    @Override
    public List<String> getProductsTitle(List<Long> categoryIds) throws NotFoundException {
        System.out.println(categoryIds);
        List<Category> categories= this.categoryRepository.findAllByIdIn(categoryIds);
        List<Product> products= this.selfProductRepository.findAllByCategoryIn(categories);

        List<String> title=new ArrayList<>();

        for (Product product : products){
            title.add(product.getTitle());
        }
        return title;
    }


    @Override
    public List<CategoryDTO> getAllCategoriesList() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categories) {
            System.out.println(category.getName());
            categoryDTOList.add(ObjectMapper.convertCategoryToCategoryDTO(category));
        }
        return categoryDTOList;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        // category.setProductList(new ArrayList<>());
        Category category1 = this.categoryRepository.save(category);
        return ObjectMapper.convertCategoryToCategoryDTO(category1);
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) throws NotFoundException {

        if (this.categoryRepository.findById(id).isPresent()) {
            Category existingCategory = this.categoryRepository.findById(id).get();
            existingCategory.setName(categoryDTO.getName());
            Category updatedCategory = this.categoryRepository.save(existingCategory);
            return ObjectMapper.convertCategoryToCategoryDTO(updatedCategory);
        } else
            throw new NotFoundException("Category does not exist with id : " + id);
    }

    @Override
    public CategoryDTO deleteCategoryById(Long id) throws NotFoundException {
        if (this.categoryRepository.findById(id).isPresent()) {
            Category deleteCategory = this.categoryRepository.findById(id).get();
            this.categoryRepository.deleteById(id);
            return ObjectMapper.convertCategoryToCategoryDTO(deleteCategory);
        } else
            throw new NotFoundException("Category does not exist with id : " + id);
    }
}
