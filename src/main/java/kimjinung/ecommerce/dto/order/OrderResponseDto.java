package kimjinung.ecommerce.dto.order;


import lombok.Data;

import java.util.Map;

@Data
public class OrderResponseDto {

    private String userId;
    private Map<String, Integer> itemCart;
    private String city;
    private String street;
    private String zipCode;

    protected OrderResponseDto() {
    }

    public OrderResponseDto(Map<String, Integer> itemCart, String city, String street, String zipCode) {
        this.itemCart = itemCart;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
