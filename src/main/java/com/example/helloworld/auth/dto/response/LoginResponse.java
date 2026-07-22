package com.example.helloworld.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private Long id;

    private String name;

    private String email;

}