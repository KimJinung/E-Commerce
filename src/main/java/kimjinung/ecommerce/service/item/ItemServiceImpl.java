package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.CategoryItem;
import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.exception.category.CategoryNotFoundException;
import kimjinung.ecommerce.exception.item.ItemNotFoundException;
import kimjinung.ecommerce.repository.CategoryRepository;
import kimjinung.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Item register(String name, int price, int stockQuantity, int discountRate, List<Integer> categories) {
        Item item = new Item(name, price, stockQuantity, discountRate);
        categoryRepository.findAllById(categories).forEach(item::addCategory);
        return itemRepository.save(item);
    }

    @Override
    public Item update(UUID id, String name, int price, int stockQuantity, int discountRate, List<Integer> categories) {
        Item item = find(id);
        item.updateName(name);
        item.updatePrice(price);
        item.updateStockQuantity(stockQuantity);
        item.updateDiscountRate(discountRate);
        item.removeAllCategory();
        categoryRepository.findAllById(categories).forEach(item::addCategory);
        return item;
    }

    @Override
    public Item find(UUID uuid) {
        return itemRepository.findById(uuid).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<Item> searchByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new CategoryNotFoundException("존재하지 않는 카테고리 입니다.");
        });
        Category result = categoryRepository.findItemByCategory(category).orElseThrow(ItemNotFoundException::new);
        return result.getItems().stream().map(CategoryItem::getItem).collect(Collectors.toList());
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
