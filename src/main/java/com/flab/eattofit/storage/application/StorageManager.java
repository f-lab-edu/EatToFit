package com.flab.eattofit.storage.application;

import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;

public interface StorageManager {

    PresignedUrlResponse getPresignedUrl(String resource, String fileName);
}
