package kimjinung.ecommerce.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemSearchByCategoriesRequestDto {
    private List<Integer> categories;
}
