package com.example.ec_app.entity;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private int orderId;
    private int userId;
    private OffsetDateTime orderDate;
    private int subTotal;
}
