package org.example.mapper;

import jakarta.validation.constraints.NotNull;
import org.example.dtos.BookCreateDTO;
import org.example.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(@NotNull BookCreateDTO dto) {
        return Book.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .price(dto.getPrice())
                .author(dto.getAuthor())
                .quantityInStock(dto.getQuantityInStock())
                .build();
    }
}
