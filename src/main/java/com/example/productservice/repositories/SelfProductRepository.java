package com.example.productservice.repositories;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelfProductRepository  extends CrudRepository<Product,Long> {

    List<Product> findAllByCategoryIn(List<Category> categories);
    public Product findByTitleEquals(String title);

    // price attribute from Price entity
    public List<Product> findByTitleEqualsAndPrice_Price(String title, double price);

    public List<Product> findAllByCategory_Id(Long id);

    long countAllByPrice_Currency(String currency);


    @Query(value = CustomQuery.GET_PRODUCT_BY_CATEGORY_ID, nativeQuery = true)
    public List<Product> getProductByCategory_Id(long id);



    @Query(value = CustomQuery.GET_ALL_PRODUCT_BY_PRICE_ID)
    public List<Product> getAllProductByPriceId(long id);

    @Query(value = CustomQuery.READ_ALL_BY_TITLE_AND_CURRENCY)
    public List<Product> readAllByTitleAndCurrency(String title,String currency);




}