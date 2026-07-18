package com.example.helloworld.service.impl;

import com.example.helloworld.entity.User;
import com.example.helloworld.repository.UserRepository;
import com.example.helloworld.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User tidak ditemukan"));

    }

    @Override
    public User create(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email sudah digunakan");
        }

        return userRepository.save(user);

    }

    @Override
    public User update(Long id, User user) {

        User existing = findById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return userRepository.save(existing);

    }

    @Override
    public void delete(Long id) {

        User user = findById(id);

        userRepository.delete(user);

    }

}