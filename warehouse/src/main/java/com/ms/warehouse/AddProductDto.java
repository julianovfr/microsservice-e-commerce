package com.ms.warehouse;

import jakarta.validation.constraints.NotBlank;

public record AddProductDto(@NotBlank String name,
                               @NotBlank Integer qtd,
                               @NotBlank String status
                            )  {
}
