package com.example.ec_app.infrastructure.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.ec_app.entity.CartItemDto;
import com.example.ec_app.infrastructure.repository.mapper.CartItemMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final CartItemMapper cartItemMapper;

    public List<CartItemDto> selectCartItems(int userId) {
        return cartItemMapper.selectCartItems(userId);
    }

    public void addCartItem(int userId, int productId, int quantity) {
        cartItemMapper.addCartItem(userId, productId, quantity);
    }

    public boolean updateQuantity(int userId, int productId, int quantity) {
        return cartItemMapper.updateQuantity(userId, productId, quantity);
    }

    public boolean removeProduct(int userId, int productId) {
        return cartItemMapper.removeProduct(userId, productId);
    }
}
