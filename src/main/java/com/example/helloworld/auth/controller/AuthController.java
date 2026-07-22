package com.example.helloworld.auth.controller;

import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
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

}