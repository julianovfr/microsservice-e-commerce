package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateProductDto(@NotBlank String name,
                               @NotBlank Integer qtd,
                               @NotBlank String status) {
}
