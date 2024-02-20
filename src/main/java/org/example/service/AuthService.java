package org.example.service;

import org.example.dtos.RefreshTokenRequest;
import org.example.dtos.auth.CustomerCreateDTO;
import org.example.dtos.auth.TokenRequest;
import org.example.dtos.auth.TokenResponse;
import org.example.entity.Customer;
import org.example.exception.DuplicateUsernameException;
import org.springframework.lang.NonNull;

public interface AuthService {
    TokenResponse generateToken(@NonNull TokenRequest tokenRequest);

    TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Customer create(CustomerCreateDTO customerCreateDTO) throws DuplicateUsernameException;

    Customer findById(Long id);
}
