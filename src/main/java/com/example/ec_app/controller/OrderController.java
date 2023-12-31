package com.example.ec_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ec_app.payload.request.OrderRequest;
import com.example.ec_app.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("checkout")
    public void checkout(@RequestBody OrderRequest order) {
        int userId = 1;// TODO 認証トークンから取得する
        orderService.checkout(userId, order);
    }
}
