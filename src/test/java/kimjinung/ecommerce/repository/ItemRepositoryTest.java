package kimjinung.ecommerce.repository;

import kimjinung.ecommerce.domain.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void before() {
        Item item = new Item("iPhone", 1000, 1, 0);
        itemRepository.save(item);
    }

    @Test
    void testSave() {
        Optional<Item> optionalItem = itemRepository.findByName("iPhone");

        assertThat(optionalItem).isPresent();
        assertThat(optionalItem.get().getName()).isEqualTo("iPhone");
        assertThat(optionalItem.get().getPrice()).isEqualTo(1000);
        assertThat(optionalItem.get().getStockQuantity()).isEqualTo(1);
        assertThat(optionalItem.get().getDiscountRate()).isEqualTo(0);
    }

}