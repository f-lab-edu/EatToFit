package com.flab.eattofit.storage.application;

public interface StorageManager {

    String getPresignedUrl(String resource, String fileName);
    String getFileUrl(String resource, String fileName);
}
