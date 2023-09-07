package com.example.ec_app.service;

import org.springframework.stereotype.Service;
import com.example.ec_app.infrastructure.repository.OrderRepository;
import com.example.ec_app.payload.request.OrderRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void checkout(int userId, final OrderRequest order) {
        order.getCartItems().stream().forEach(cartItem -> {
            int subTotal =
                    cartItem.getProduct().getPrice() * cartItem.getQuantity();
            // TODO トランザクション管理
            int orderId = orderRepository.saveOrderItem(userId, subTotal);
            orderRepository.saveOrderDetail(orderId,
                    cartItem.getProduct().getProductId(),
                    cartItem.getQuantity(), cartItem.getProduct().getPrice());
        });

    }
}
