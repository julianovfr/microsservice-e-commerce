package com.ms.email.dtos;

import java.util.UUID;

public record EmailRecordDto(UUID userId, String emailTO,
                             String subject, String text) {
}
