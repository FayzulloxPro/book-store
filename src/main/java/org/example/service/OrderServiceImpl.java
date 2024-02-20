package org.example.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.config.security.SessionUser;
import org.example.dtos.CreateOrderRequestDTO;
import org.example.entity.Book;
import org.example.entity.Order;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final AuthService authService;

    private final SessionUser sessionUser;

    @Override
    public Order createOrder(@NotNull CreateOrderRequestDTO dto) {
        List<Long> bookIds = dto.getBooks();

        List<Book> books = bookService.getAllBooksByIds(bookIds);
        if (books.isEmpty()) throw new ItemNotFoundException("Books found: 0");
        Order order = Order.builder()
                .orderDate(new Date())
                .books(books)
                .customer(authService.findById(sessionUser.id()))
                .build();

        return orderRepository.save(order);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
