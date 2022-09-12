package com.javaabuser.onlinestore.controllers;

import com.javaabuser.onlinestore.models.Category;
import com.javaabuser.onlinestore.models.Product;
import com.javaabuser.onlinestore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public void create(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @PostMapping("/update/{category_name}")
    public void update(@RequestBody Category categoryToBeUpdated, @PathVariable("category_name") String categoryName){
        Category category = new Category();

        category.setProducts(categoryToBeUpdated.getProducts());
        category.setTitle(categoryToBeUpdated.getTitle());

        categoryRepository.save(category);
    }

    public List<Product> showProducts(Category category){
        return category.getProducts();
    }

    public void delete(@RequestBody Category category){
        categoryRepository.delete(category);
    }

}
