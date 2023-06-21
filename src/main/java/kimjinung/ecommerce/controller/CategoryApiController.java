package kimjinung.ecommerce.controller;


import kimjinung.ecommerce.domain.item.Category;
import kimjinung.ecommerce.dto.BaseResponseDto;
import kimjinung.ecommerce.dto.category.*;
import kimjinung.ecommerce.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    public BaseResponseDto<CategoryRegistrationResponseDto> register(
            @RequestBody @Validated CategoryRegistrationRequestDto dto,
            BindingResult bindingResult
    ) {

        Category category = categoryService.register(dto.getName(), dto.getParentId());

        return new BaseResponseDto<>(
                200,
                new CategoryRegistrationResponseDto(
                        category.getId(),
                        category.getName(),
                        category.getParent() != null ? category.getParent().getId() : 0,
                        category.getParent() != null ? category.getParent().getName() : ""
                )
        );
    }

    @GetMapping
    public BaseResponseDto<CategorySearchResponseDto> search(
            @RequestBody @Validated CategorySearchRequestDto dto,
            BindingResult bindingResult
    ) {

        int id = dto.getId();
        Category result = categoryService.findById(id);
        return new BaseResponseDto<>(
                200,
                new CategorySearchResponseDto(
                        result.getId(),
                        result.getName(),
                        result.getParent() == null ? "" : result.getParent().getName()
                )

        );
    }

    @PatchMapping
    public BaseResponseDto<CategoryUpdateResponseDto> update(
            @RequestBody @Validated CategoryUpdateRequestDto dto,
            BindingResult bindingResult
    ) {
        Category result = categoryService.update(dto.getId(), dto.getName(), dto.getParentId());

        return new BaseResponseDto<>(
                200,
                new CategoryUpdateResponseDto(
                        result.getId(),
                        result.getParent() == null ? 0 : result.getParent().getId(),
                        result.getName(),
                        result.getParent() == null ? "" : result.getParent().getName()
                )
        );
    }

}
