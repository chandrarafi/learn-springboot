package com.example.helloworld.service.interfaces;
import com.example.helloworld.dto.request.UserRequest;
import com.example.helloworld.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
