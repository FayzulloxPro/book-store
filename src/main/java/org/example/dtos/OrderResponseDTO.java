package org.example.dtos;

import lombok.*;
import org.example.entity.Book;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderResponseDTO {
    private Long id;


    private CustomerResponseDTO customer;

    private List<Book> books;

    private Date orderDate;
}
