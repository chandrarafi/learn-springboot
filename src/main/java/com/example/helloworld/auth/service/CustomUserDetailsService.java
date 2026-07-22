package com.example.helloworld.auth.service;

import com.example.helloworld.User.entity.User;
import com.example.helloworld.User.repository.UserRepository;
import com.example.helloworld.auth.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Email tidak ditemukan"
                        ));

        return new CustomUserDetails(user);
    }
}