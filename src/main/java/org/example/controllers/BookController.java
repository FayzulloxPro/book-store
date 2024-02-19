package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.BookCreateDTO;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.repository.BookRepository;
import org.example.service.BookStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final BookStoreService service;
    private final BookMapper bookMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> create(BookCreateDTO dto) {
        Book book = bookMapper.toEntity(dto);
        book = service.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long bookId) {
        service.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
