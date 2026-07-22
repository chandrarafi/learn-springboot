package com.example.helloworld.User.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.entity.User;
import com.example.helloworld.User.mapper.UserMapper;
import com.example.helloworld.User.repository.UserRepository;
import com.example.helloworld.User.service.interfaces.UserService;
import com.example.helloworld.exception.NotFoundException;
import com.example.helloworld.exception.DuplicateResourceException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


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
        log.info("Searching users keyword={}", keyword);
        return users.map(userMapper::toResponse);

    }

    @Override
    public UserResponse findById(Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("User tidak ditemukan"));
        log.info("Searching users keyword={}", id);
        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse create(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email sudah digunakan");
        }
        
        log.info("Creating user with email: {}", request.getEmail());
        User user = userMapper.toEntity(request);
        user.setPassword(
        passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        log.info("User created successfully with id: {}", user.getId());
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User tidak ditemukan"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null &&
            !request.getPassword().isBlank()) {

            user.setPassword(
                passwordEncoder.encode(request.getPassword())
            );
        }
        user = userRepository.save(user);
        log.info("Updating user with id {}", id);
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
        log.info("Deleting user with id {}", id);

    }

    @Override
    @Transactional
    public void restore(Long id) {

        userRepository.findIncludingDeleted(id)
                .orElseThrow(() -> new NotFoundException("User tidak ditemukan"));

        userRepository.restoreById(id);
        log.info("Restoring user with id {}", id);

    }

    @Override
    @Transactional
    public void permanentDelete(Long id) {

        userRepository.findIncludingDeleted(id)
                .orElseThrow(() -> new NotFoundException("User tidak ditemukan"));

        userRepository.permanentDeleteById(id);
        log.info("Permanently deleting user with id {}", id);

    }

}