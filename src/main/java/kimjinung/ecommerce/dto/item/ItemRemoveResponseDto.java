package kimjinung.ecommerce.dto.item;

import lombok.Data;

@Data
public class ItemRemoveResponseDto {

    private String id;
    private String result;


    protected ItemRemoveResponseDto() {
    }

    public ItemRemoveResponseDto(String id, String result) {
        this.id = id;
        this.result = result;
    }
}
