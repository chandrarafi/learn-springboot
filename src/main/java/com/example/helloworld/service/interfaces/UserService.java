package com.example.helloworld.service.interfaces;

import java.util.List;

import com.example.helloworld.dto.request.UserRequest;
import com.example.helloworld.dto.response.UserResponse;

public interface UserService 
{
    List<UserResponse> findAll();

    UserResponse findById(Long id);
    
    UserResponse create(UserRequest user);
    
    UserResponse update(Long id, UserRequest user);
    
    void delete(Long id);
}
