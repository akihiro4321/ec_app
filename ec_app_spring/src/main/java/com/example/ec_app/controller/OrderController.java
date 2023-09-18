package com.example.ec_app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ec_app.model.auth.LoginUserDetails;
import com.example.ec_app.payload.request.OrderRequest;
import com.example.ec_app.payload.response.OrderHistoryResponse;
import com.example.ec_app.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("checkout")
    public void checkout(final Authentication auth, @RequestBody final OrderRequest order) {
        final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
        final Integer userId = userDetails.getUserId();
        orderService.checkout(userId, order);
    }

    @GetMapping("history")
    public OrderHistoryResponse getOrderHistory(final Authentication auth) {
        final LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
        final Integer userId = userDetails.getUserId();
        return orderService.getOrderHistory(userId);
    }
}
