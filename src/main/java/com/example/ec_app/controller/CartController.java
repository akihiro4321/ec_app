package com.example.ec_app.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
  public CartResponse getCart() {
    final int userId = 1;// TODO 認証トークンから取得する
    return cartService.selectCartItems(userId);
  }

  @PostMapping("/addToCart") // TODO レスポンス考える
  public void addToCart(@RequestBody final AddCartRequest request) {
    final int userId = 1;// TODO 認証トークンから取得する
    cartService.addToCart(userId, request.getProductId(),
        request.getQuantity());
  }

  @PutMapping("/changeQuantity") // TODO レスポンス考える
  public void changeQuantity(@RequestBody final ChangeCartRequest request) {
    final int userId = 1;// TODO 認証トークンから取得する
    cartService.updateQuantity(userId, request.getProductId(),
        request.getQuantity());
  }

  @DeleteMapping("/removeFromCart")
  public void removeFromCart(
      @RequestParam(name = "cartItemId") final int cartItemId) {
    final int userId = 1;// TODO 認証トークンから取得する
    cartService.removeFromCart(userId, cartItemId);
  }

}
