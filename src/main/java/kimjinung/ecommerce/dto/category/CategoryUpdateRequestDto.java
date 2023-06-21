package kimjinung.ecommerce.dto.category;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto {

    private int id;
    private String name;
    private int parentId;

}
