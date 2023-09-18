package com.example.ec_app.model;

import lombok.Data;

@Data
public class CartItem {
  private int id;
  private int quantity;
  private Product product;
}
