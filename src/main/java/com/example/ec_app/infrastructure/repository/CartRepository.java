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

    public List<CartItemDto> selectCartItems(final int userId) {
        return cartItemMapper.selectCartItems(userId);
    }

    public void addCartItem(final int userId, final int productId,
            final int quantity) {
        cartItemMapper.addCartItem(userId, productId, quantity);
    }

    public boolean updateQuantity(final int userId, final int productId,
            final int quantity) {
        return cartItemMapper.updateQuantity(userId, productId, quantity);
    }

    public boolean removeProduct(final int userId, final int productId) {
        return cartItemMapper.removeProduct(userId, productId);
    }

    public boolean removeCartItems(final List<Integer> cartItemIdList) {
        return cartItemMapper.removeCartItems(cartItemIdList);
    }
}
