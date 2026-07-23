package com.example.helloworld.auth.service;

import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.request.RefreshTokenRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
import com.example.helloworld.auth.dto.response.RefreshTokenResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void logout(RefreshTokenRequest request);

}