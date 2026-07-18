package com.example.helloworld.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloworld.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
    