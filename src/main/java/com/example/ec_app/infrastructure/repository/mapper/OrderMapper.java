package com.example.ec_app.infrastructure.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.ec_app.entity.OrderItemDto;

@Mapper
public interface OrderMapper {

        public int insertOrderItem(OrderItemDto orderItemDto);

        public int insertOrderDetail(@Param("orderId") int orderId,
                        @Param("productId") int productId,
                        @Param("quantity") int quantity,
                        @Param("orderPrice") int orderPrice);
}
