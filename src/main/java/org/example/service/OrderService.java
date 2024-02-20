package org.example.service;

import jakarta.validation.constraints.NotNull;
import org.example.dtos.CreateOrderRequestDTO;
import org.example.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    Order createOrder(@NotNull CreateOrderRequestDTO dto);

    Page<Order> getAllOrders(Pageable pageable);
}
