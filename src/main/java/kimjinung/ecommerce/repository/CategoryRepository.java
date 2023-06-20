package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.item.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
