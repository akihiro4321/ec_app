package com.example.ec_app.service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ec_app.entity.OrderDetailDto;
import com.example.ec_app.entity.OrderItemDto;
import com.example.ec_app.infrastructure.repository.CartRepository;
import com.example.ec_app.infrastructure.repository.OrderRepository;
import com.example.ec_app.model.CartItem;
import com.example.ec_app.model.Order;
import com.example.ec_app.model.OrderedProduct;
import com.example.ec_app.payload.request.OrderRequest;
import com.example.ec_app.payload.response.OrderHistoryResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void checkout(final int userId, final OrderRequest order) {
        final OffsetDateTime checkoutDt = OffsetDateTime.now();
        final OrderItemDto orderDto = OrderItemDto.builder().userId(userId)
                .orderDate(checkoutDt).totalCost(order.getTotalCost()).build();
        orderRepository.saveOrderItem(orderDto);
        order.getCartItems().stream().forEach(cartItem -> {
            orderRepository.saveOrderDetail(orderDto.getOrderId(),
                    cartItem.getProduct().getProductId(),
                    cartItem.getQuantity(), cartItem.getProduct().getPrice());
        });
        final List<Integer> cartItemIdList = order.getCartItems().stream()
                .map(CartItem::getId).collect(Collectors.toList());
        cartRepository.removeCartItems(cartItemIdList);
    }

    public OrderHistoryResponse getOrderHistory(final int userId) {
        List<Order> orders;
        final List<OrderItemDto> orderItems =
                orderRepository.selectOrderItemsByUserId(userId);
        if (!orderItems.isEmpty()) {
            final List<Integer> orderIds = orderItems.stream()
                    .map(OrderItemDto::getOrderId).collect(Collectors.toList());
            final List<OrderDetailDto> orderDetails =
                    orderRepository.selectOrderDetails(orderIds);
            orders = orderItems.stream().map(orderItem -> {
                final List<OrderedProduct> products = orderDetails.stream()
                        .filter(e -> e.getOrderId() == orderItem
                                .getOrderId())
                        .map(detail -> {
                            return OrderedProduct.builder()
                                    .productId(detail
                                            .getProduct()
                                            .getProductId())
                                    .productName(
                                            detail.getProduct()
                                                    .getProductName())
                                    .imageUrl(detail.getProduct()
                                            .getImageUrl())
                                    .orderPrice(detail
                                            .getOrderPrice())
                                    .quantity(detail.getQuantity())
                                    .build();
                        }).collect(Collectors.toList());
                return new Order(orderItem, products);
            }).collect(Collectors.toList());
        } else {
            orders = Collections.emptyList();
        }

        final OrderHistoryResponse res = new OrderHistoryResponse(orders);
        return res;
    }
}
