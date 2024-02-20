package org.example.service;

import org.example.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAllBooksByIds(List<Long> bookIds);

    Book create(Book book);


    void deleteById(Long bookId);

    Page<Book> getAllBooks(Pageable pageable);
}
