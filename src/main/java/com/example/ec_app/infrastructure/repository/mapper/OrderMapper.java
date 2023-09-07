package com.example.ec_app.infrastructure.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    public int insertOrderItem(@Param("userId") int userId,
            @Param("subTotal") int subTotal);

    public int insertOrderDetail(@Param("orderId") int orderId,
            @Param("productId") int productId, @Param("quantity") int quantity,
            @Param("orderPrice") int orderPrice);
}
