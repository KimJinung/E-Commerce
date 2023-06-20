package kimjinung.ecommerce.service.item;

import kimjinung.ecommerce.dto.item.ItemRegistrationRequestDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationResponseDto;

public interface ItemService {
    ItemRegistrationResponseDto register(ItemRegistrationRequestDto dto);
}
