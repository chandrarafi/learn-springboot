package com.example.helloworld.User.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserRequest {
    @Schema(
        description = "Nama lengkap user",
        example = "Rafi Chandra"
    )
    @NotBlank(message = "Nama wajib diisi")
    @Size(min = 3, max = 100, message = "Nama harus 3-100 karakter")
    private String name;

    @Schema(
        description = "Email user",
        example = "rafi@gmail.com"
    )
    @NotBlank(message = "Email wajib diisi")
    @Email(message = "Format email tidak valid")
    @Size(max = 150, message = "Email maksimal 150 karakter")
    private String email;
}