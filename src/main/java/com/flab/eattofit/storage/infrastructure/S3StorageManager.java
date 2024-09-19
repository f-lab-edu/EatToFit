package com.flab.eattofit.storage.infrastructure;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.flab.eattofit.storage.application.StorageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class S3StorageManager implements StorageManager {

    private static final String KEY_FORMAT = "%s/%s";
    private static final String S3_URL_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    @Value("${cloud.aws.s3.presigned-url-expiration-period}")
    private int expiration;

    private final AmazonS3 amazonS3;

    @Override
    public String getPresignedUrl(final String resource, final String fileName) {
        String key = generateKey(resource, fileName);
        URL presignedUrl = generatePresignedUrl(key);
        return presignedUrl.toString();
    }

    private String generateKey(final String resource, final String fileName) {
        return String.format(KEY_FORMAT, resource, fileName);
    }

    private URL generatePresignedUrl(final String key) {
        GeneratePresignedUrlRequest presignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(getExpiration());

        return amazonS3.generatePresignedUrl(presignedUrlRequest);
    }

    private Date getExpiration() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        Instant expirationInstant = now.plusMinutes(expiration)
                .toInstant(ZoneOffset.UTC);
        return Date.from(expirationInstant);
    }

    @Override
    public String getFileUrl(final String resource, final String fileName) {
        String key = generateKey(resource, fileName);
        return String.format(S3_URL_FORMAT, bucket, amazonS3.getRegionName(), key);
    }
}
