package com.example.ec_app.model;

import lombok.Data;

@Data
public class Product {
  private int productId;
  private String productName;
  private String imageUrl;
  private int price;
  private String description;
}
