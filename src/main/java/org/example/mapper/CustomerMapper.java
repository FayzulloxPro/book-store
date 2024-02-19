package org.example.mapper;

import org.example.dtos.auth.CustomerCreateDTO;
import org.example.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerCreateDTO customerCreateDTO) {
        return Customer.builder()
                .email(customerCreateDTO.email())
                .password(customerCreateDTO.password())
                .name(customerCreateDTO.name())
                .build();
    }
}
