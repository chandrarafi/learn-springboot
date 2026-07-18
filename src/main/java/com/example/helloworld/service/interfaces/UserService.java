package com.example.helloworld.service.interfaces;

import java.util.List;

import com.example.helloworld.entity.User;

public interface UserService 
{
    List<User> findAll();

    User findById(Long id);
    
    User create(User user);
    
    User update(Long id, User user);
    
    void delete(Long id);
}
