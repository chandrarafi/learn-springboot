package com.example.helloworld.mapper;

import com.example.helloworld.dto.request.UserRequest;
import com.example.helloworld.dto.response.UserResponse;
import com.example.helloworld.entity.User;

public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

}