package tech.bestwebshop.api.categoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.categoryservice.exception.ResourceNotFoundException;
import tech.bestwebshop.api.categoryservice.model.Category;
import tech.bestwebshop.api.categoryservice.model.CategoryDTO;
import tech.bestwebshop.api.categoryservice.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO newCategory) {
        try {
            Category category = categoryRepository.save(new Category(0, newCategory.getName()));
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable(value = "id") Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            categoryRepository.deleteById(optionalCategory.get().getId());
            return ResponseEntity.accepted().body(optionalCategory.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
