package kimjinung.ecommerce.service.order;

import kimjinung.ecommerce.domain.item.Item;
import kimjinung.ecommerce.domain.member.Member;
import kimjinung.ecommerce.domain.order.Address;
import kimjinung.ecommerce.domain.order.ItemOrder;
import kimjinung.ecommerce.domain.order.Order;
import kimjinung.ecommerce.dto.order.OrderRequestDto;
import kimjinung.ecommerce.dto.order.OrderResponseDto;
import kimjinung.ecommerce.exception.MemberNotFoundException;
import kimjinung.ecommerce.repository.ItemRepository;
import kimjinung.ecommerce.repository.MemberRepository;
import kimjinung.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Override
    public OrderResponseDto order(OrderRequestDto dto) {
        Member member = memberRepository.findById(dto.getUserId()).orElseThrow(() -> {
            throw new MemberNotFoundException("Member not found");
        });

        Map<String, Integer> itemCart = dto.getItemCart();
        List<Item> items = itemCart.keySet()
                .stream()
                .map(id -> itemRepository.findById(UUID.fromString(id)).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (items.isEmpty()) throw new IllegalStateException();

        Order order = new Order(member, new Address(dto.getCity(), dto.getStreet(), dto.getZipCode()));
        items.forEach(item -> order.addItem(item, itemCart.get(item.getId().toString())));
        order.done();
        orderRepository.save(order);

        Map<String, Integer> result = new HashMap<>();
        for (ItemOrder item: order.getItems()) {
            String name = item.getItem().getName();
            Integer count = item.getCount();
            result.put(name, count);
        }

        return new OrderResponseDto(result, order.getTotalPrice());
    }
}
