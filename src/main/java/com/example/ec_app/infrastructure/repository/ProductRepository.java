package com.example.ec_app.infrastructure.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.ec_app.entity.ProductDto;
import com.example.ec_app.infrastructure.repository.mapper.ProductsMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductsMapper productsMapper;

    public List<ProductDto> findAll() {
        return productsMapper.findAll();
    }
}
