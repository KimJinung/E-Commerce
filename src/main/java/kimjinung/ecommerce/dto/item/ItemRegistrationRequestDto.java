package kimjinung.ecommerce.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemRegistrationRequestDto {

    private String name;
    private int price;
    private int stockQuantity;
    private int discountRate;
    private List<Integer> categories;

}
