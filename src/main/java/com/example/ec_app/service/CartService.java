package com.example.ec_app.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.ec_app.infrastructure.repository.CartRepository;
import com.example.ec_app.model.CartItem;
import com.example.ec_app.payload.response.CartResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;

    public CartResponse selectCartItems(final int userId) {
        final List<CartItem> cartItems = cartRepository.selectCartItems(userId)
                .stream().map(cartItemDto -> {
                    final CartItem cartItem =
                            modelMapper.map(cartItemDto, CartItem.class);
                    return cartItem;
                }).collect(Collectors.toList());
        final CartResponse res = new CartResponse();
        res.setCartItems(cartItems);
        return res;
    }

    public void addToCart(final int userId, final int productId,
            final int quantity) {
        cartRepository.addCartItem(userId, productId, quantity);
    }

    public void updateQuantity(final int userId, final int productId,
            final int quantity) {
        cartRepository.updateQuantity(userId, productId, quantity);
    }

    public void removeFromCart(final int userId, final int productId) {
        cartRepository.removeProduct(userId, productId);
    }
}
