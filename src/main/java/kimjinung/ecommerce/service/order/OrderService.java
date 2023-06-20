package kimjinung.ecommerce.service.order;

import kimjinung.ecommerce.dto.order.OrderRequestDto;
import kimjinung.ecommerce.dto.order.OrderResponseDto;

public interface OrderService {
    OrderResponseDto order(OrderRequestDto dto);
}
