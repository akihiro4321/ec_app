package com.example.ec_app.model;

import java.util.List;
import com.example.ec_app.entity.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private OrderItemDto orderItem;
    private List<OrderedProduct> products;
}
