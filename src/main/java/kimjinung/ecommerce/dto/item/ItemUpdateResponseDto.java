package kimjinung.ecommerce.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemUpdateResponseDto {

    private String id;
    private String name;
    private int price;
    private int stockQuantity;
    private int discountRate;
    private List<String> categories;


    protected ItemUpdateResponseDto() {
    }

    public ItemUpdateResponseDto(
            String id, String name, int price, int stockQuantity, int discountRate, List<String> categories
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.discountRate = discountRate;
        this.categories = categories;
    }
}
