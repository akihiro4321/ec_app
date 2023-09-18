package com.example.ec_app.payload.request;

import lombok.Data;

@Data
public class ChangeCartRequest {
    private int productId;
    private int quantity;
}
