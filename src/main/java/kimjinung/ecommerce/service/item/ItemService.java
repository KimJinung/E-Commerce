package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    Item register(Item item, List<Category> categories);
    Item update(Item item, List<Category> categories);
    Item searchById(UUID uuid);
    List<Item> searchByCategory(Category categories);
    List<Item> searchByKeyword(String keywords);
    void remove(Item item);
}
