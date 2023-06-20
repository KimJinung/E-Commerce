package kimjinung.ecommerce.dto.order;


import lombok.Data;

import java.util.Map;

@Data
public class OrderResponseDto {

    private Map<String, Integer> itemCart;
    private long totalPrice;

    protected OrderResponseDto() {
    }

    public OrderResponseDto(Map<String, Integer> itemCart, long totalPrice) {
        this.itemCart = itemCart;
        this.totalPrice = totalPrice;
    }
}
