package kimjinung.ecommerce.dto.item;

import lombok.Data;

@Data
public class ItemUpdateRequestDto {

    private String id;
    private String name;
    private int price;
    private int stockQuantity;
    private int discountRate;

    protected ItemUpdateRequestDto() {
    }

    public ItemUpdateRequestDto(String id, String name, int price, int stockQuantity, int discountRate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.discountRate = discountRate;
    }
}
