package com.example.ec_app.infrastructure.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.ec_app.entity.ProductDto;

@Mapper
public interface ProductsMapper {
    public List<ProductDto> findAll();
}
