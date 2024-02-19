package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Book> books;

    private Date orderDate;
}
