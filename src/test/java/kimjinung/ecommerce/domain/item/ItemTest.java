package kimjinung.ecommerce.domain.item;

import kimjinung.ecommerce.exception.item.NotEnoughItemStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class ItemTest {

    Item item;

    @BeforeEach
    void before() {
        item = new Item("iPhone", 9999999, 10, 0);
    }

    @Test
    void testAddCategory() {
        Category digital = new Category(null, "Digital");
        item.addCategory(digital);
        CategoryItem categoryItem = item.getCategories()
                .stream()
                .findFirst()
                .orElse(null);

        assertThat(categoryItem).isNotNull();
        assertThat(categoryItem.getCategory().getName()).isEqualTo("Digital");
    }

    @Test
    void testRemoveCategory() {
        Category digital = new Category(null, "Digital");
        item.addCategory(digital);
        item.removeCategory(digital);
        List<CategoryItem> categories = item.getCategories();

        assertThat(categories).isEmpty();
    }

    @Test
    void testAddStock() {
        item.addStock(10);

        assertThat(item.getStockQuantity()).isEqualTo(20);
    }

    @Test
    void testReduceStock() {
        item.reduceStock(3);

        assertThat(item.getStockQuantity()).isEqualTo(7);
    }

    @Test
    void testReduceStock_NotEnoughStock() {
        assertThrows(NotEnoughItemStockException.class,
                () -> item.reduceStock(20)
        );
    }

    @Test
    void testCalculatePriceByCount() {
        Item foo = new Item("foo", 10000, 10, 10);
        Long totalPrice = foo.calculatePriceByCount(2);

        assertThat(totalPrice).isEqualTo(18000L);

    }
}