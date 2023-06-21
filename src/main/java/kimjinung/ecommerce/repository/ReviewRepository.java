package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.item.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
