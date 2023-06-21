package kimjinung.ecommerce.dto.category;

import lombok.Data;

@Data
public class CategorySearchResponseDto {

    private int id;
    private String name;
    private String parentName;

    public CategorySearchResponseDto(int id, String name, String parentName) {
        this.id = id;
        this.name = name;
        this.parentName = parentName;
    }
}
