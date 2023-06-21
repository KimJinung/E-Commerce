package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    Item register(String name, int price, int stockQuantity, int discountRate, List<Integer> category);
    Item update(UUID id, String name, int price, int stockQuantity, int discountRate, List<Integer> category);
    Item find(UUID uuid);
    List<Item> searchByCategory(Category categories);
    List<Item> searchByKeyword(String keywords);
    void remove(Item item);
}
