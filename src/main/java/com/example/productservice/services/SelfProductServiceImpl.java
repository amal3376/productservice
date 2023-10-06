package com.example.productservice.services;

import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.SelfProductRepository;
import com.example.productservice.utill.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SelfProductServiceImpl implements SelfProduct {

    private SelfProductRepository selfProductRepository;
    private PriceRepository priceRepository;

    private CategoryRepository categoryRepository;

    public SelfProductServiceImpl(SelfProductRepository selfProductRepository, PriceRepository priceRepository, CategoryRepository categoryRepository) {
        this.selfProductRepository = selfProductRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Iterable<Product> products = this.selfProductRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product prods : products) {
            productDTOList.add(ObjectMapper.convertProductToProductDto(prods));
        }

        //this.selfProductRepository.findById()
        return productDTOList;
    }

    @Override
    public ProductDTO getById(Long id) throws NotFoundException {

        Optional<Product> product = this.selfProductRepository.findById(id);

        if (product.isEmpty())
            throw new NotFoundException("Product does not exist with id : " + id);

        return ObjectMapper.convertProductToProductDto(product.get());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertProductDtoToProduct(productDTO);
        this.selfProductRepository.save(product);

        System.out.println(product.toString());
        return ObjectMapper.convertProductToProductDto(product);

    }

    @Override
    @Transactional
    public ProductDTO deleteProductById(Long id) throws NotFoundException {
        Optional<Product> product = this.selfProductRepository.findById(id);
        if (product.isPresent()) {
            this.selfProductRepository.deleteById(id);
        } else {
            throw new NotFoundException("Product does not exist with id : " + id);
        }


        return ObjectMapper.convertProductToProductDto(product.get());

    }

    @Override
    public ProductDTO updateProductById(Long id, ProductDTO productDTO) throws NotFoundException {
        if (this.selfProductRepository.findById(id).isPresent()) {
            Product existingProduct = this.selfProductRepository.findById(id).get();
            this.selfProductRepository.save(existingProduct);
            return ObjectMapper.convertProductToProductDto(existingProduct);

        } else
            throw new NotFoundException("Product does not exist with id : " + id);
    }




    public Product convertProductDtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(product.getDescription());
        product.setImage(productDTO.getImage());


        // Setting price from ProductDTO
        Price price = new Price();
        price.setPrice(productDTO.getPrice().getPrice());
        price.setCurrency(productDTO.getPrice().getCurrency());
        product.setPrice(price);

        // Setting Category from ProductDTO
        Category category = null;
        if (this.categoryRepository.getCategoryByName(productDTO.getCategory()).isEmpty())
        {
            category = new Category();
            category.setName(productDTO.getCategory());
        }

        product.setCategory(category);

        return product;
    }

}
