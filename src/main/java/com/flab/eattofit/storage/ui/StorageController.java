package com.flab.eattofit.storage.ui;

import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import com.flab.eattofit.storage.application.StorageService;
import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/storage")
@RestController
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/presigned-url")
    public ResponseEntity<PresignedUrlResponse> getPresignedUrl(
            @AuthMember Long memberId,
            @RequestParam("resource") final String resource,
            @RequestParam("name") final String name
    ) {
        return ResponseEntity.ok()
                .body(storageService.getPresignedUrl(memberId, resource, name));
    }
}
