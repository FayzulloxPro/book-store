package org.example.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookCreateDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 255, message = "Author cannot exceed 255 characters")
    private String author;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 255, message = "Genre cannot exceed 255 characters")
    private String genre;

    @Positive(message = "Price cannot be negative")
    private double price;


    @Min(value = 0, message = "Quantity must be at least 0")
    @Max(value = 1000, message = "Quantity cannot exceed 1000")
    private int quantityInStock;
}
