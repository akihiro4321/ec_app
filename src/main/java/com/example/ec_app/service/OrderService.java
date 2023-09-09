package com.example.ec_app.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.ec_app.entity.OrderItemDto;
import com.example.ec_app.infrastructure.repository.CartRepository;
import com.example.ec_app.infrastructure.repository.OrderRepository;
import com.example.ec_app.model.CartItem;
import com.example.ec_app.payload.request.OrderRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public void checkout(int userId, final OrderRequest order) {
        OffsetDateTime checkoutDt = OffsetDateTime.now();
        OrderItemDto orderDto = OrderItemDto.builder().userId(userId)
                .orderDate(checkoutDt).totalCost(order.getTotalCost()).build();
        // TODO トランザクション管理
        orderRepository.saveOrderItem(orderDto);
        order.getCartItems().stream().forEach(cartItem -> {
            orderRepository.saveOrderDetail(orderDto.getOrderId(),
                    cartItem.getProduct().getProductId(),
                    cartItem.getQuantity(), cartItem.getProduct().getPrice());
        });
        List<Integer> cartItemIdList = order.getCartItems().stream()
                .map(CartItem::getId).collect(Collectors.toList());
        cartRepository.removeCartItems(cartItemIdList);
    }
}
