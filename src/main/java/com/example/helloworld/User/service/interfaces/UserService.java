package com.example.helloworld.User.service.interfaces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;

public interface UserService 
{
    Page<UserResponse> findAll(
            String keyword,
            Pageable pageable
    );
    
    UserResponse findById(Long id);
    
    UserResponse create(UserRequest user);
    
    UserResponse update(Long id, UserRequest user);
    
    void delete(Long id);

    void restore(Long id);

    void permanentDelete(Long id);
}
