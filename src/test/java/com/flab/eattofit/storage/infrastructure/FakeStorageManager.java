package com.flab.eattofit.storage.infrastructure;

import com.flab.eattofit.storage.application.StorageManager;

public class FakeStorageManager implements StorageManager {

    private static final String FAKE_STORAGE = "http://fake.storage.com";

    @Override
    public String getPresignedUrl(final String resource, final String fileName) {
        String fileUrl = FAKE_STORAGE + "/" + resource + "/" + fileName;
        return fileUrl + "?type=fake";
    }

    @Override
    public String getFileUrl(final String resource, final String fileName) {
        return FAKE_STORAGE + "/" + resource + "/" + fileName;
    }
}
