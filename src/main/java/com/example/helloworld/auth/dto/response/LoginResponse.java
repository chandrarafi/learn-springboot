package com.example.helloworld.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonPropertyOrder({ "id", "name", "email", "roles", "accessToken", "refreshToken", "tokenType" })
public class LoginResponse {

    private Long id;

    private String name;

    private String email;

    private List<String> roles;

    private String accessToken;

    private String refreshToken;

    private String tokenType;

}