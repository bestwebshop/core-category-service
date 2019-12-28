package tech.bestwebshop.api.categoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.categoryservice.exception.ResourceNotFoundException;
import tech.bestwebshop.api.categoryservice.model.Category;
import tech.bestwebshop.api.categoryservice.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<Category> getAllCategories(){ return categoryRepository.findAll();}

    @PostMapping("/category")
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Integer categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId)
        );
    }

    @DeleteMapping("/category/{id}")
    public Category deleteCategory(@PathVariable(value="id") Integer categoryId){
        Category categoryToDelete = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId)
        );
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }

}
