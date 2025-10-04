package com.ms.user.dtos;

import com.ms.user.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//record encapsula varios getter e setter
public record UserRecordDto(@NotBlank String name,
                            @NotBlank @Email String email,
                            @NotBlank String password,
                            @NotBlank String role) {
}
