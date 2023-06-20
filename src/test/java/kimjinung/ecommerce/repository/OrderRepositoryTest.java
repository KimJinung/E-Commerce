package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.member.Member;
import kimjinung.ecommerce.domain.order.Address;
import kimjinung.ecommerce.domain.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class OrderRepositoryTest {

    Member member;
    Order order;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void before() {
        saveMember();
        saveOrder();
    }

    private void saveOrder() {
        order = new Order(member, new Address("a", "bb", "1234"));
        order.done();
    }

    private void saveMember() {
        member = new Member("jinung", "1234", "e@email.com");
        memberRepository.save(member);
    }

    @Test
    void testFindByMember() {
        List<Order> orders = orderRepository.findByMember(member).orElse(null);

        Assertions.assertThat(orders).isNotNull();
        Assertions.assertThat(orders).isEmpty();
    }

}