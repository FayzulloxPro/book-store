package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.BookRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookRepository bookRepository;


    private final CustomerRepository customerRepository;


    private final OrderRepository orderRepository;
}
