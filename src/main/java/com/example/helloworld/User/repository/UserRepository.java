package com.example.helloworld.User.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloworld.User.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsByEmail(String email);

    Page<User> findByNameContainingIgnoreCase(
        String keyword,
        Pageable pageable
    );
}
    