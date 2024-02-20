package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;
    @Column(name = "price")
    private double price;
    @Column
    private int quantityInStock;

}
