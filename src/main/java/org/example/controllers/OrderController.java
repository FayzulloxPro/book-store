package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.CreateOrderRequestDTO;
import org.example.dtos.OrderResponseDTO;
import org.example.entity.Order;
import org.example.mapper.OrderMapper;
import org.example.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO dto) {
        Order order = orderService.createOrder(dto);
        OrderResponseDTO orderResponseDTO = orderMapper.toDto(order);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        Page<Order> allOrders = orderService.getAllOrders(pageable);
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
}
