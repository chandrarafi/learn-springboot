package com.example.helloworld.User.controller;
import org.springframework.web.bind.annotation.*;

import com.example.helloworld.User.dto.request.UserRequest;
import com.example.helloworld.User.dto.response.UserResponse;
import com.example.helloworld.User.service.interfaces.UserService;
import com.example.helloworld.common.response.ApiResponse;
import com.example.helloworld.common.response.ResponseBuilder;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "User",
        description = "User Management API"
)
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Get All Users",
        description = "Menampilkan seluruh user dengan pagination"
    )
    @GetMapping
    public ApiResponse<?> findAll(
        @RequestParam(
        required = false, name = "keyword" )
        String keyword,

        @PageableDefault(size = 10)
        Pageable pageable
    ) {

        return ResponseBuilder.page(
                userService.findAll(keyword, pageable)
        );

    }

    @Operation(
        summary = "Get User",
        description = "Mencari user berdasarkan ID"
    )
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(
            @PathVariable("id") Long id
    ) {

        return ResponseBuilder.success(
            userService.findById(id)
        );

    }

    @Operation(
        summary = "Create User",
        description = "Membuat user baru"
)
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

    @Operation(
        summary = "Update User"
    )
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

    @Operation(
        summary = "Delete User",
        description = "Soft delete user berdasarkan ID"
    )
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable("id") Long id
    ) {
        userService.delete(id);
        return ResponseBuilder.success(
            "User berhasil di hapus"
        );
    }

    @Operation(
        summary = "Restore User",
        description = "Memulihkan user yang sudah di soft delete"
    )
    @PatchMapping("/{id}/restore")
    public ApiResponse<Void> restore(
            @PathVariable("id") Long id
    ) {
        userService.restore(id);
        return ResponseBuilder.success(
            "User berhasil dipulihkan"
        );
    }

    @Operation(
        summary = "Permanent Delete User",
        description = "Menghapus user secara permanen dari database"
    )
    @DeleteMapping("/{id}/permanent")
    public ApiResponse<Void> permanentDelete(
            @PathVariable("id") Long id
    ) {
        userService.permanentDelete(id);
        return ResponseBuilder.success(
            "User berhasil dihapus secara permanen"
        );
    }
}
