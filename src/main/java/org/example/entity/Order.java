package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;
    /*
        @ManyToMany(cascade = CascadeType.REMOVE)*/
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "orders_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> books;

    private Date orderDate;
}
