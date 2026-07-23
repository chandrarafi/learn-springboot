package com.example.helloworld.auth.service.impl;

import com.example.helloworld.User.entity.User;
import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
import com.example.helloworld.auth.security.CustomUserDetails;
import com.example.helloworld.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import com.example.helloworld.auth.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

        private final AuthenticationManager authenticationManager;

        private final JwtService jwtService;

        @Override
        public LoginResponse login(LoginRequest request) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword())
                        );
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                User user = userDetails.getUser();
                String accessToken = jwtService.generateToken(userDetails);

                return LoginResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .accessToken(accessToken)
                        .tokenType("Bearer")
                        .build();
                }
}