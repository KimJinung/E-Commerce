package kimjinung.ecommerce.dto.order;

import lombok.Data;

import javax.print.attribute.IntegerSyntax;
import java.util.Map;

@Data
public class OrderRequestDto {

    private String userId;
    private Map<String, Integer> itemCart;
    private String city;
    private String street;
    private String zipCode;
}
