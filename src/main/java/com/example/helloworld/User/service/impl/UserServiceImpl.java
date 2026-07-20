package com.example.helloworld.User.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.entity.User;
import com.example.helloworld.User.mapper.UserMapper;
import com.example.helloworld.User.repository.UserRepository;
import com.example.helloworld.User.service.interfaces.UserService;
import com.example.helloworld.exception.NotFoundException;
import com.example.helloworld.exception.DuplicateResourceException;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> findAll(
            String keyword,
            Pageable pageable
    ) {

        Page<User> users;

        if (keyword == null || keyword.isBlank()) {

            users = userRepository.findAll(pageable);

        } else {

            users = userRepository.findByNameContainingIgnoreCase(
                    keyword,
                    pageable
            );

        }

        return users.map(userMapper::toResponse);

    }

    @Override
    public UserResponse findById(Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("User tidak ditemukan"));

        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse create(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email sudah digunakan");
        }
        User user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User tidak ditemukan"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user = userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }

    @Override
    public void delete(Long id) {

        UserResponse user = findById(id);

        userRepository.deleteById(user.getId());

    }

}