package kimjinung.ecommerce.controller;


import kimjinung.ecommerce.dto.BaseResponseDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationRequestDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationResponseDto;
import kimjinung.ecommerce.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController {

    private final ItemService itemService;

//    @PostMapping
//    public BaseResponseDto<ItemRegistrationResponseDto> register(
//            @RequestBody @Validated ItemRegistrationRequestDto dto,
//            BindingResult bindingResult
//    ) {
//
//
//    }
}
