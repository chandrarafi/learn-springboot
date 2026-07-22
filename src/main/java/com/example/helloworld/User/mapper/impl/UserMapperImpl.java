package com.example.helloworld.User.mapper.impl;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.entity.User;
import com.example.helloworld.User.mapper.UserMapper;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .enabled(true)
                .accountNonLocked(true)
                .build();

    }

    @Override
    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .accountNonLocked(user.getAccountNonLocked())
                .build();

    }

}