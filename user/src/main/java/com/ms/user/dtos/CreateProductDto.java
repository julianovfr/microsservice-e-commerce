package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;

import java.net.http.HttpRequest;

public record CreateProductDto(@NotBlank String name,
                               @NotBlank Integer qtd,
                               @NotBlank String status)  {
}
