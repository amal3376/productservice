package com.example.productservice.repositories;
import com.example.productservice.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {



    List<Category> findAll();
    public Optional<Category> getCategoryByName(String name);

    public Optional<Category> findByName(String name);

    public List<Category> findAllByIdIn(List<Long> id);




}