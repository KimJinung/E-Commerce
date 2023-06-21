package kimjinung.ecommerce.dto.item;


import lombok.Data;

import java.util.List;

@Data
public class ItemSearchByKeywordsRequestDto {

    private List<String> keywords;
}
