package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.member.Member;
import kimjinung.ecommerce.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<List<Order>> findByMember(Member member);
}
