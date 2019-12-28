package tech.bestwebshop.api.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.bestwebshop.api.categoryservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
