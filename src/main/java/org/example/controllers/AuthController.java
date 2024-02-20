package org.example.controllers;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.CustomerResponseDTO;
import org.example.dtos.RefreshTokenRequest;
import org.example.dtos.auth.CustomerCreateDTO;
import org.example.dtos.auth.TokenRequest;
import org.example.dtos.auth.TokenResponse;
import org.example.entity.Customer;
import org.example.exception.DuplicateUsernameException;
import org.example.mapper.CustomerMapper;
import org.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    private final CustomerMapper customerMapper;

    @PostMapping("/access/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody @Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateToken(tokenRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(@Nonnull @Valid @RequestBody CustomerCreateDTO customerCreateDTO) throws DuplicateUsernameException {
        Customer customer = authService.create(customerCreateDTO);
        return ResponseEntity.ok(customerMapper.toDTO(customer));
    }
}
