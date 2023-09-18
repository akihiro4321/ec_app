package com.example.ec_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderedProduct {
    private int productId;
    private String productName;
    private String imageUrl;
    private int orderPrice;
    private int quantity;
}
