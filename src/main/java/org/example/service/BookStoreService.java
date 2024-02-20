package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.exception.ItemNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookRepository bookRepository;


    private final CustomerRepository customerRepository;


    private final OrderRepository orderRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
