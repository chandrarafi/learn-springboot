package com.example.helloworld.User.controller;
import org.springframework.web.bind.annotation.*;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.service.interfaces.UserService;
import com.example.helloworld.common.ApiResponse;
import com.example.helloworld.common.ResponseBuilder;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<?> findAll(
            @RequestParam(required = false)
            String keyword,

            @PageableDefault(size = 10)
            Pageable pageable
    ) {

        return ResponseBuilder.page(
                userService.findAll(keyword, pageable)
        );

    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(
            @PathVariable("id") Long id
    ) {

        return ResponseBuilder.success(
            userService.findById(id)
        );

    }

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid
            @RequestBody UserRequest request
    ) {

        return ResponseBuilder.success(
            "User berhasil dibuat",
            userService.create(request)
        );

    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody UserRequest request
    ) {

        return ResponseBuilder.success(
                "User berhasil diupdate",
                userService.update(id, request)
        );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable("id") Long id
    ) {

        userService.delete(id);

        return ResponseBuilder.success(
            "User berhasil di hapus"
        );
    }
}
