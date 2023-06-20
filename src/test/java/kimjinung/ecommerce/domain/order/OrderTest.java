package kimjinung.ecommerce.domain.order;

import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.domain.member.Member;
import kimjinung.ecommerce.exception.NotEnoughItemStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    Member member;
    Item item;

    @BeforeEach
    void before() {
        member = new Member("jinung", "0410", "jinung@outllok.com");
        item = new Item("iPhone", 100, 10, 0);
        order = new Order(member, new Address("city", "street", "1234"));
    }

    @Test
    void testAddItem() {
        order.addItem(item, 2);

        assertThat(order.getItems().size()).isEqualTo(1);
        assertThat(order.getTotalPrice()).isEqualTo(200);
    }

    @Test
    void testAddItem_NotEnoughStock() {
        assertThrows(NotEnoughItemStockException.class,
                () -> order.addItem(item, 1000)
        );
    }

}