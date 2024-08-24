package com.flab.eattofit.member.ui.auth;

import com.flab.eattofit.member.application.auth.AuthService;
import com.flab.eattofit.member.application.auth.dto.LoginRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.ui.auth.support.annotations.OAuthAuthority;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid final LoginRequest request,
                                      @OAuthAuthority final OAuthProviderRequest provider) {
        String token = authService.login(request, provider);
        System.out.println("token = " + token);
        return ResponseEntity.ok().build();
    }
}
