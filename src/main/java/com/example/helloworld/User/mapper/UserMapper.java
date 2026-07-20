package com.example.helloworld.User.mapper;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.entity.User;

public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

}