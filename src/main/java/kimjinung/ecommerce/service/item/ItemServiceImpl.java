package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.exception.item.ItemNotFoundException;
import kimjinung.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item register(Item item, List<Category> categories) {
        categories.forEach(item::addCategory);
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item update(Item item, List<Category> categories) {
        Item foundItem = searchById(item.getId());
        foundItem.updateName(item.getName());
        foundItem.updatePrice(item.getPrice());
        foundItem.updateDiscountRate(item.getDiscountRate());
        foundItem.updateStockQuantity(item.getStockQuantity());
        foundItem.getCategories().clear();
        categories.forEach(item::addCategory);
        return foundItem;
    }

    @Override
    public Item searchById(UUID uuid) {
        return itemRepository.findById(uuid).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<Item> searchByCategory(Category category) {
        return itemRepository.findAllByCategory(category).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<Item> searchByKeyword(String keyword) {
        return itemRepository.findAllByKeyword(keyword).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public void remove(Item item) {
        itemRepository.delete(item);
    }
}
