package kimjinung.ecommerce.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemRegistrationResponseDto {

    private String name;
    private int price;
    private int stockQuantity;
    private List<String> categories;

    protected ItemRegistrationResponseDto() {
    }

    public ItemRegistrationResponseDto(String name, int price, int stockQuantity, List<String> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }
}
