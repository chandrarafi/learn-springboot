package com.example.helloworld.auth.service;

import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}