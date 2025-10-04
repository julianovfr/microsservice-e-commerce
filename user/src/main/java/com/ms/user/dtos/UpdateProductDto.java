package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductDto(@NotBlank String name,
                               @NotBlank Integer qtd
                              ) {
}
