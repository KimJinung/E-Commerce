package kimjinung.ecommerce.dto.review;

import lombok.Data;

@Data
public class ReviewRegistrationRequestDto {

    private String userId;
    private String itemId;
    private String text;
}
