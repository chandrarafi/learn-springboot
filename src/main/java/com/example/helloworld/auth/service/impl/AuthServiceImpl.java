package com.example.helloworld.auth.service.impl;

import com.example.helloworld.User.entity.User;
import com.example.helloworld.User.repository.UserRepository;
import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
import com.example.helloworld.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())

        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }

}