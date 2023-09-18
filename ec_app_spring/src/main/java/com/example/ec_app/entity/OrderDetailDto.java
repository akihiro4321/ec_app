package com.example.ec_app.entity;

import com.example.ec_app.model.Product;
import lombok.Data;

@Data
public class OrderDetailDto {
    private int orderDetailId;
    private int orderId;
    private Product product;
    private int quantity;
    private int orderPrice;
}
