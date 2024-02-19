package org.example.controllers;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.example.dtos.RefreshTokenRequest;
import org.example.dtos.auth.AuthUserCreateDTO;
import org.example.dtos.auth.TokenRequest;
import org.example.dtos.auth.TokenResponse;
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

    @PostMapping("/access/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateToken(tokenRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@Nonnull @Valid @RequestBody AuthUserCreateDTO authUserCreateDTO) throws DuplicateUsernameException {
        return ResponseEntity.ok(authService.create(authUserCreateDTO));
    }
}
