package kimjinung.ecommerce.controller;


import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.dto.ResponseDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationRequestDto;
import kimjinung.ecommerce.dto.item.ItemRegistrationResponseDto;
import kimjinung.ecommerce.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping
    public ResponseDto<ItemRegistrationResponseDto> register(
            @RequestBody @Validated ItemRegistrationRequestDto dto,
            BindingResult bindingResult
    ) {

        Item result = itemService.register(
                dto.getName(), dto.getPrice(), dto.getStockQuantity(), dto.getDiscountRate(), dto.getCategories()
        );

        return new ResponseDto<>(200,
                new ItemRegistrationResponseDto(
                        result.getName(),
                        result.getPrice(),
                        result.getStockQuantity(),
                        result.getCategories()
                                .stream()
                                .map(categoryItem -> categoryItem.getCategory().getName())
                                .collect(Collectors.toList())

                ));
    }
}
