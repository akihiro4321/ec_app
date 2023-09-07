package com.example.ec_app.payload.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class ProductResponse {
  private Integer productId;
  private String description;
  private String imageUrl;
  private String productName;
  private BigDecimal price;
  private Integer status;
  private OffsetDateTime createdAt;
  private Integer categoryId;
}
