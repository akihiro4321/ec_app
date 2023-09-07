package com.example.ec_app.model;

import lombok.Data;

@Data
public class CartItem {
  private long id;
  private int quantity;
  private Product product;
}
