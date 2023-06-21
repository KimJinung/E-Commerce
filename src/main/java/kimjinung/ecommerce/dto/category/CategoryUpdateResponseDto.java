package kimjinung.ecommerce.dto.category;

import lombok.Data;

@Data
public class CategoryUpdateResponseDto {

    private int id;
    private String name;
    private int parentId;
    private String parentName;

    public CategoryUpdateResponseDto(int id, int parentId, String name, String parentName) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.parentName = parentName;
    }
}
