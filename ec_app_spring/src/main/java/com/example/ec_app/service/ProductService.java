package com.example.ec_app.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.ec_app.infrastructure.repository.ProductRepository;
import com.example.ec_app.payload.response.ProductResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        final List<ProductResponse> res =
                productRepository.findAll().stream().map(productDto -> {
                    final ProductResponse productRes =
                            modelMapper.map(productDto, ProductResponse.class);
                    return productRes;
                }).collect(Collectors.toList());
        return res;
    }
}
