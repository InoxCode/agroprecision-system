package com.agroprecision.identity.infrastructure.adapter.in.rest;

import com.agroprecision.identity.application.port.in.LoginUseCase;
import com.agroprecision.identity.application.service.LoginResult;
import com.agroprecision.identity.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.agroprecision.identity.infrastructure.adapter.in.rest.dto.LoginResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        LoginResult result =
                loginUseCase.login(
                        request.email(),
                        request.password()
                );

        LoginResponse response =
                new LoginResponse(
                        result.success(),
                        result.blocked(),
                        result.message(),
                        result.failedAttempts()
                );

        if (result.success()) {
            return ResponseEntity.ok(response);
        }

        if (result.blocked()) {
            return ResponseEntity
                    .status(HttpStatus.LOCKED)
                    .body(response);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}