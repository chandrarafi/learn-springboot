package com.example.helloworld.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({ "id", "name", "email", "accessToken", "tokenType" })
public class LoginResponse {

    private Long id;

    private String name;

    private String email;

    private String accessToken;

    private String tokenType;

}