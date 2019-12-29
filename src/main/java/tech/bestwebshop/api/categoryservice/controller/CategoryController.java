package tech.bestwebshop.api.categoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.categoryservice.exception.ResourceNotFoundException;
import tech.bestwebshop.api.categoryservice.model.Category;
import tech.bestwebshop.api.categoryservice.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        System.out.println("#### Get category by ID " + categoryId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/categories/{id}")
    public Category deleteCategory(@PathVariable(value = "id") Integer categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId)
        );
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }

}
