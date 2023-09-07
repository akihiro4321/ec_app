package com.example.ec_app.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class ProductDto {
    private Integer productId;
    private String description;
    private String imageUrl;
    private String productName;
    private BigDecimal price;
    private Integer status;
    private OffsetDateTime createdAt;
    private Integer categoryId;
}
