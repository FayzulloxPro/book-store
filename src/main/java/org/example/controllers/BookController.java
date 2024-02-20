package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.BookCreateDTO;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {


    private final BookService service;
    private final BookMapper bookMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> create(@RequestBody @Valid BookCreateDTO dto) {
        Book book = bookMapper.toEntity(dto);
        book = service.create(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long bookId) {
        service.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        return new ResponseEntity<>(service.getAllBooks(pageable), HttpStatus.OK);
    }
}
