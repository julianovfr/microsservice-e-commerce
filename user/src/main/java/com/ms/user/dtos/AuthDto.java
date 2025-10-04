package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(@NotBlank String email,
                      @NotBlank String password) {
}
