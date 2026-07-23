package com.example.helloworld.auth.controller;

import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.request.RefreshTokenRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
import com.example.helloworld.auth.dto.response.RefreshTokenResponse;
import com.example.helloworld.auth.service.AuthService;
import com.example.helloworld.common.response.ApiResponse;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        LoginResponse response = authService.login(request);

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login berhasil")
                .data(response)
                .build();

    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {

        RefreshTokenResponse response = authService.refreshToken(request);

        return ApiResponse.<RefreshTokenResponse>builder()
                .success(true)
                .message("Token berhasil diperbarui")
                .data(response)
                .build();

    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(
            @Valid @RequestBody RefreshTokenRequest request
    ) {

        authService.logout(request);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Logout berhasil")
                .build();

    }

}