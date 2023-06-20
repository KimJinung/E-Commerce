package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
