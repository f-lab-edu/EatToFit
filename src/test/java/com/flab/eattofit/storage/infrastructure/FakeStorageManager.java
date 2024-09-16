package com.flab.eattofit.storage.infrastructure;

import com.flab.eattofit.storage.application.StorageManager;
import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;

public class FakeStorageManager implements StorageManager {

    private static final String FAKE_STORAGE = "http://fake.storage.com";

    @Override
    public PresignedUrlResponse getPresignedUrl(final String resource, final String fileName) {
        String fileUrl = FAKE_STORAGE + "/" + resource + "/" + fileName;
        String presignedUrl = fileUrl + "?type=fake";
        return new PresignedUrlResponse(presignedUrl, fileUrl);
    }
}
