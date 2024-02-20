package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dtos.auth.CustomerCreateDTO;
import org.example.entity.Book;
import org.example.entity.Customer;
import org.example.exception.DuplicateUsernameException;
import org.example.repository.BookRepository;
import org.example.repository.CustomerRepository;
import org.example.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBMockDataLoader {
    private final AuthService authService;
    private final CustomerRepository customerRepository;

    @Bean
    public CommandLineRunner initData(BookRepository bookRepository) throws DuplicateUsernameException {
        createDefaultAdminAndUser();
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Read books from JSON file in resources folder
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("books.json");
                if (inputStream != null) {
                    List<Book> books = objectMapper.readValue(inputStream,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));

                    // Save books to the database
                    bookRepository.saveAll(books);

                    System.out.println("Books saved to the database successfully!");
                } else {
                    System.err.println("Error reading JSON file: books.json not found in resources folder");
                }
            } catch (IOException e) {
                System.err.println("Error reading JSON file: " + e.getMessage());
            }
        };
    }

    public void createDefaultAdminAndUser() throws DuplicateUsernameException {
        // create a new user
        CustomerCreateDTO dto = new CustomerCreateDTO("password", "admin", "admin@mail.ru");
        authService.create(dto);

        //make it admin
        Customer byId = authService.findById(1L);
        byId.setAdmin(true);
        customerRepository.save(byId);

        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("password", "user", "user@mail.ru");
        authService.create(customerCreateDTO);
    }


}
