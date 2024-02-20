package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.exception.ItemNotFoundException;
import org.example.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book not found with id: " + bookId));
        bookRepository.deleteBookInTables(bookId);
        bookRepository.delete(book);
    }
    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
