package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "DELETE FROM orders_book WHERE book_id = :bookId", nativeQuery = true)
    void deleteBookInTables(@Param("bookId") Long bookId);

}