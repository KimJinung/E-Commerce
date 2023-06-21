package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.item.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);

    @Query(value = "SELECT c from Category c WHERE c = :category")
    Optional<Category> findItemByCategory(Category category);
}
