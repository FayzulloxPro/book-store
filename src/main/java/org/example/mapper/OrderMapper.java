package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dtos.OrderResponseDTO;
import org.example.entity.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CustomerMapper customerMapper;
    public OrderResponseDTO toDto(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customer(customerMapper.toDTO(order.getCustomer()))
                .books(order.getBooks())
                .orderDate(order.getOrderDate())
                .build();
    }
}
