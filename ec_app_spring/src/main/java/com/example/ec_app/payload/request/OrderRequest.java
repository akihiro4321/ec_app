package com.example.ec_app.payload.request;

import java.util.List;
import com.example.ec_app.model.CartItem;
import lombok.Data;

@Data
public class OrderRequest {
    private List<CartItem> cartItems;
    private int totalCost;
}
