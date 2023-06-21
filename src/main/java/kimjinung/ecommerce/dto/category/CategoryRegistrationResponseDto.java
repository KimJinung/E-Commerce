package kimjinung.ecommerce.dto.category;

import kimjinung.ecommerce.domain.item.Category;
import lombok.Data;

@Data
public class CategoryRegistrationResponseDto {

    private int id;
    private String parentName;
    private String name;

    protected CategoryRegistrationResponseDto() {
    }

    public CategoryRegistrationResponseDto(int id, String parentName, String name) {
        this.id = id;
        this.parentName = parentName;
        this.name = name;
    }
}
