package tech.bestwebshop.api.categoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.categoryservice.model.Category;
import tech.bestwebshop.api.categoryservice.model.CategoryDTO;
import tech.bestwebshop.api.categoryservice.repository.CategoryRepository;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    @RolesAllowed({"USER"})
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO newCategory) {
        try {
            Category category = categoryRepository.save(new Category(0, newCategory.getName()));
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/categories/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/categories/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Category> updateCategoryById(@PathVariable(value = "id") Integer categoryId,
                                                       @RequestBody @Valid Category categoryToUpdate){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            if (!optionalCategory.get().equals(categoryToUpdate)) {
                try {
                    Category category = categoryRepository.save(categoryToUpdate);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(category);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalCategory.get());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/categories/{id}")
    @RolesAllowed({"ADMIN"})
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
