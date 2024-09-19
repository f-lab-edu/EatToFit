package com.flab.eattofit.storage.infrastructure.dto;

public record PresignedUrlResponse(
        String presignedUrl,
        String fileUrl
) {
}
