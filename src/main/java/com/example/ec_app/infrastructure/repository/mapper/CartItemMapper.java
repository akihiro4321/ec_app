package com.example.ec_app.infrastructure.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.ec_app.entity.CartItemDto;

@Mapper
public interface CartItemMapper {

        public List<CartItemDto> selectCartItems(@Param("userId") int userId);

        public void addCartItem(@Param("userId") int userId,
                        @Param("productId") int productId,
                        @Param("quantity") int quantity);

        public boolean updateQuantity(@Param("userId") int userId,
                        @Param("productId") int productId,
                        @Param("quantity") int quantity);

        public boolean removeProduct(@Param("userId") int userId,
                        @Param("productId") int productId);

        public boolean removeCartItems(
                        @Param("cartItemIdList") List<Integer> cartItemIdList);
}
