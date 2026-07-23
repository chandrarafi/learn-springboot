package com.example.helloworld.User.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "id", "name", "email", "enabled", "accountNonLocked" })
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private Boolean enabled;

    private Boolean accountNonLocked;


}