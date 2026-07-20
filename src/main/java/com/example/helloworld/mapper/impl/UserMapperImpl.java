package com.example.helloworld.mapper.impl;

import com.example.helloworld.dto.request.UserRequest;
import com.example.helloworld.dto.response.UserResponse;
import com.example.helloworld.entity.User;
import com.example.helloworld.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

    }

    @Override
    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }

}