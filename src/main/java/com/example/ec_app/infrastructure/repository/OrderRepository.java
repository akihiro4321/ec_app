package com.example.ec_app.infrastructure.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.ec_app.entity.OrderDetailDto;
import com.example.ec_app.entity.OrderItemDto;
import com.example.ec_app.infrastructure.repository.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderMapper orderMapper;

    public int saveOrderItem(final OrderItemDto orderItemDto) {
        return orderMapper.insertOrderItem(orderItemDto);
    }

    public int saveOrderDetail(final int orderId, final int productId,
            final int quantity, final int orderPrice) {
        return orderMapper.insertOrderDetail(orderId, productId, quantity,
                orderPrice);
    };

    public List<OrderItemDto> selectOrderItemsByUserId(final int userId) {
        return orderMapper.selectOrderItemsByUserId(userId);
    }

    public List<OrderDetailDto> selectOrderDetails(
            final List<Integer> orderIds) {
        return orderMapper.selectOrderDetails(orderIds);
    }

}
