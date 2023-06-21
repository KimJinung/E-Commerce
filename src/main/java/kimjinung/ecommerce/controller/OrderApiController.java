package kimjinung.ecommerce.controller;


import kimjinung.ecommerce.dto.ResponseDto;
import kimjinung.ecommerce.dto.order.OrderRequestDto;
import kimjinung.ecommerce.dto.order.OrderResponseDto;
import kimjinung.ecommerce.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/order")
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public ResponseDto<OrderResponseDto> order(
            @RequestBody @Validated OrderRequestDto dto,
            BindingResult bindingResult
    ) {

        OrderResponseDto result = orderService.order(dto);
        return new ResponseDto<>(200, result);
    }
}
