package kimjinung.ecommerce.controller;


import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.dto.ResponseDto;
import kimjinung.ecommerce.dto.item.*;
import kimjinung.ecommerce.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController extends BaseApiController{

    private final ItemService itemService;

    @PostMapping
    public ResponseDto<ItemRegistrationResponseDto> register(
            @RequestBody @Validated ItemRegistrationRequestDto dto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);

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

    @GetMapping("/keyword")
    public ResponseDto<List<ItemSearchByKeywordsResponseDto>> searchByKeyword(@RequestParam("keyword") String keyword) {
        List<Item> items = itemService.searchByKeyword(keyword);

        List<ItemSearchByKeywordsResponseDto> result = items.stream()
                .map(item -> new ItemSearchByKeywordsResponseDto(
                        item.getId().toString(),
                        item.getName(),
                        item.getPrice(),
                        item.getStockQuantity(),
                        item.getDiscountRate()))
                .collect(Collectors.toList());

        return new ResponseDto<>(200, result);
    }

    @GetMapping("/category")
    public ResponseDto<List<ItemSearchByCategoryResponseDto>> searchByCategory(@RequestParam("category") int category) {
        List<Item> items = itemService.searchByCategory(category);

        List<ItemSearchByCategoryResponseDto> result = items.stream()
                .map(item -> new ItemSearchByCategoryResponseDto(
                        item.getId().toString(),
                        item.getName(),
                        item.getPrice(),
                        item.getStockQuantity(),
                        item.getDiscountRate())
                ).collect(Collectors.toList());

        return new ResponseDto<>(200, result);
    }

    @PatchMapping
    public ResponseDto<ItemUpdateResponseDto> update(
            @RequestBody @Validated ItemUpdateRequestDto dto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);

        Item update = itemService.update(
                UUID.fromString(dto.getId()),
                dto.getName(),
                dto.getPrice(),
                dto.getStockQuantity(),
                dto.getDiscountRate(),
                dto.getCategories()
        );

        return new ResponseDto<>(
                200,
                new ItemUpdateResponseDto(
                        update.getId().toString(),
                        update.getName(),
                        update.getPrice(),
                        update.getStockQuantity(),
                        update.getDiscountRate(),
                        update.getCategories().stream().map(i -> i.getCategory().getName()).collect(Collectors.toList())
                )
        );
    }

    @DeleteMapping
    public ResponseDto<ItemRemoveResponseDto> remove(
            @RequestBody @Validated ItemRemoveRequestDto dto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        itemService.remove(UUID.fromString(dto.getItemId()));

        return new ResponseDto<>(
                200,
                new ItemRemoveResponseDto(
                        dto.getItemId(),
                        "ok"
                )
        );
    }

}

