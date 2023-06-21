package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByName(String name);

    @Query(value = "SELECT i FROM Item i WHERE i.name LIKE %:keyword%")
    Optional<List<Item>> findAllByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT i FROM Item i WHERE :category in i.categories")
    Optional<List<Item>> findAllByCategory(@Param("category") Category category);
}
