package com.example.ec_app.infrastructure.repository;

import org.springframework.stereotype.Repository;
import com.example.ec_app.infrastructure.repository.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderMapper orderMapper;

    public int saveOrderItem(int userId, int subTotal) {
        return orderMapper.insertOrderItem(userId, subTotal);
    }

    public int saveOrderDetail(int orderId, int productId, int quantity,
            int orderPrice) {
        return orderMapper.insertOrderDetail(orderId, productId, quantity,
                orderPrice);
    };


}
