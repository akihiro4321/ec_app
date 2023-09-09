package com.example.ec_app.payload.response;

import java.util.List;
import com.example.ec_app.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryResponse {
    private List<Order> orders;
}
