package com.example.ec_app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ec_app.model.auth.LoginUserDetails;
import com.example.ec_app.payload.request.AddCartRequest;
import com.example.ec_app.payload.request.ChangeCartRequest;
import com.example.ec_app.payload.response.CartResponse;
import com.example.ec_app.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

  private final CartService cartService;

  @GetMapping("")
  public CartResponse getCart(final Authentication auth) {
    final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
    final Integer userId = userDetails.getUserId();
    return cartService.selectCartItems(userId);
  }

  @PostMapping("/addToCart") // TODO レスポンス考える
  public void addToCart(final Authentication auth, @RequestBody final AddCartRequest request) {
    final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
    final Integer userId = userDetails.getUserId();
    cartService.addToCart(userId, request.getProductId(),
        request.getQuantity());
  }

  @PutMapping("/changeQuantity") // TODO レスポンス考える
  public void changeQuantity(final Authentication auth,
      @RequestBody final ChangeCartRequest request) {
    final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
    final Integer userId = userDetails.getUserId();
    cartService.updateQuantity(userId, request.getProductId(),
        request.getQuantity());
  }

  @DeleteMapping("/removeFromCart")
  public void removeFromCart(final Authentication auth,
      @RequestParam(name = "cartItemId") final int cartItemId) {
    final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
    final Integer userId = userDetails.getUserId();
    cartService.removeFromCart(userId, cartItemId);
  }

}
