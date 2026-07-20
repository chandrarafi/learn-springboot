package com.example.helloworld.service.impl;

import com.example.helloworld.entity.User;
import com.example.helloworld.mapper.UserMapper;
import com.example.helloworld.repository.UserRepository;
import com.example.helloworld.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.helloworld.dto.request.UserRequest;
import com.example.helloworld.dto.response.UserResponse;
import com.example.helloworld.exception.NotFoundException;
import com.example.helloworld.exception.DuplicateResourceException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toResponse)
            .toList();
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