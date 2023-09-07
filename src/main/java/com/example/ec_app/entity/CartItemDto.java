package com.example.ec_app.entity;

import com.example.ec_app.model.Product;
import lombok.Data;

@Data
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
}
