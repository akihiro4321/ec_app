package com.example.ec_app.payload.response;

import java.util.List;

import com.example.ec_app.model.CartItem;

import lombok.Data;

@Data
public class CartResponse {
  private List<CartItem> cartItems;
}
