package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.domain.item.CategoryItem;
import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.dto.item.ItemRegistrationRequestDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationResponseDto;
import kimjinung.ecommerce.exception.ItemNotFoundException;
import kimjinung.ecommerce.repository.CategoryRepository;
import kimjinung.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemRegistrationResponseDto register(ItemRegistrationRequestDto dto) {
        Item item = createItemByDto(dto);
        itemRepository.save(item);

        Item result = itemRepository.findById(item.getId()).orElseThrow(() -> {
            throw new ItemNotFoundException("Fail to register item");
        });
        List<String> categories = result.getCategories()
                .stream()
                .map(categoryItem -> categoryItem.getCategory().getName())
                .collect(Collectors.toList());

        return new ItemRegistrationResponseDto(
                result.getName(),
                result.getPrice(),
                result.getStockQuantity(),
                categories
        );
    }

    private Item createItemByDto(ItemRegistrationRequestDto dto) {
        String name = dto.getName();
        int price = dto.getPrice();
        int stockQuantity = dto.getStockQuantity();
        int discountRate = dto.getDiscountRate();
        List<Category> categories = categoryRepository.findAllById(dto.getCategories());
        Item item = new Item(name, price, stockQuantity, discountRate);
        item.addCategory(categories);
        return item;
    }

}
