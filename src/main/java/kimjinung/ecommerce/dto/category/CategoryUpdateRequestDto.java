package kimjinung.ecommerce.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryUpdateRequestDto {

    private int id;
    private String name;

    @JsonProperty(value = "parent_id")
    private int parentId;


}
