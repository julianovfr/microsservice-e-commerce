package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record StockStatus(@NotBlank String status) {
}
