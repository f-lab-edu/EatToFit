package com.flab.eattofit.storage.application;

import com.flab.eattofit.storage.domain.ExtensionType;
import com.flab.eattofit.storage.domain.ResourceType;
import com.flab.eattofit.storage.exception.exceptions.BadFileNameException;
import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StorageService {

    private static final String EXTENSION_MARK = ".";
    private static final String MEMBER_MARK = "-";

    private final StorageManager storageManager;

    public PresignedUrlResponse getPresignedUrl(final Long memberId, final String resource, final String name) {
        ResourceType resourceType = ResourceType.findByName(resource);
        String fileName = generateFileName(memberId, name);
        return storageManager.getPresignedUrl(memberId, resourceType.getName(), fileName);
    }
    
    private String generateFileName(final Long memberId, final String name) {
        int extensionIndex = getExtensionIndexFromFileName(name);

        String originName = name.substring(0, extensionIndex);
        String extension = name.substring(extensionIndex + 1);
        ExtensionType extensionType = ExtensionType.findByName(extension);

        return originName + MEMBER_MARK + memberId + EXTENSION_MARK + extensionType.getName();
    }

    private int getExtensionIndexFromFileName(final String name) {
        int extensionIndex = name.lastIndexOf(EXTENSION_MARK);
        if (extensionIndex == -1 || extensionIndex == name.length() - 1) {
            throw new BadFileNameException();
        }
        return extensionIndex;
    }
}
