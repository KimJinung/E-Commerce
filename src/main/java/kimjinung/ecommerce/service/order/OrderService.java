package kimjinung.ecommerce.service.order;

import kimjinung.ecommerce.dto.order.OrderRequestDto;

public interface OrderService {
    OrderRequestDto order(OrderRequestDto dto);
}
