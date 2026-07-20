package com.example.helloworld.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.helloworld.dto.response.UserResponse;
import com.example.helloworld.service.interfaces.UserService;
import com.example.helloworld.common.ApiResponse;
import com.example.helloworld.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> findAll() {
    return ApiResponse.<List<UserResponse>>builder()
            .success(true)
            .message("Success")
            .data(userService.findAll())
            .build();

    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(
            @PathVariable("id") Long id
    ) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Success")
                .data(userService.findById(id))
                .build();

    }

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid
            @RequestBody UserRequest request
    ) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User berhasil dibuat")
                .data(userService.create(request))
                .build();

    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody UserRequest request
    ) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User berhasil diupdate")
                .data(userService.update(id, request))
                .build();

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable("id") Long id
    ) {

        userService.delete(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("User berhasil dihapus")
                .build();

    }
}
