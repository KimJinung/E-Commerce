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

        int parentId = dto.getParentId();
        String name = dto.getName();
        Category parent = categoryService.findById(parentId);
        Category category = new Category(parent, name);
        Category result = categoryService.register(category);
        String parentName = result.getParent() != null ? result.getParent().getName() : "";

        return new BaseResponseDto<>(
                200,
                new CategoryRegistrationResponseDto(result.getId(), parentName, result.getName()));
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
                new CategorySearchResponseDto(result.getId(), result.getName(), result.getParent().getName())
        );
    }

    @PatchMapping
    public BaseResponseDto<CategoryUpdateResponseDto> update(
            @RequestBody @Validated CategoryUpdateRequestDto dto,
            BindingResult bindingResult
    ) {
        Category parent = categoryService.findById(dto.getParentId());
        Category category = categoryService.findById(dto.getId());
        category.updateName(dto.getName());
        category.updateParent(parent);

        return new BaseResponseDto<>(
                200,
                new CategoryUpdateResponseDto(
                        category.getId(),
                        category.getParent().getId(),
                        category.getName(),
                        category.getParent().getName()
                )
        );
    }
}
