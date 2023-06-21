package kimjinung.ecommerce.dto.category;

import kimjinung.ecommerce.domain.item.Category;
import lombok.Data;

@Data
public class CategoryRegistrationResponseDto {

    private int id;
    private String name;
    private int parentId;
    private String parentName;

    protected CategoryRegistrationResponseDto() {
    }

    public CategoryRegistrationResponseDto(int id, String name, int parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
    }
}
