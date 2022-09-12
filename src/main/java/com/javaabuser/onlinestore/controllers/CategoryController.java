package com.javaabuser.onlinestore.controllers;

import com.javaabuser.onlinestore.exceptions.category.CategoryNotFoundException;
import com.javaabuser.onlinestore.models.Category;
import com.javaabuser.onlinestore.models.Product;
import com.javaabuser.onlinestore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Category> category = categoryRepository.findByTitle(categoryName);

        if(category.isPresent()){
            category.get().setProducts(categoryToBeUpdated.getProducts());
            category.get().setTitle(categoryToBeUpdated.getTitle());

            categoryRepository.save(category.get());
        }

        else {
            throw new CategoryNotFoundException();
        }
    }

    @GetMapping("/products")
    public List<Product> showProducts(Category category){
        return category.getProducts();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Category category){
        categoryRepository.delete(category);
    }

}
