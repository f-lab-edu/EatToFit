package com.flab.eattofit.sports.ui.sports;

import com.flab.eattofit.sports.application.sports.SportsService;
import com.flab.eattofit.sports.application.sports.dto.SportsCreateRequest;
import com.flab.eattofit.sports.domain.sports.Sports;
import com.flab.eattofit.sports.ui.sports.dto.SportsCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/admin/sports")
@RestController
public class SportsController {

    private final SportsService sportsService;

    @PostMapping
    public ResponseEntity<SportsCreateResponse> createSports(@RequestBody @Valid final SportsCreateRequest request) {
        Sports sports = sportsService.createSports(request);
        return ResponseEntity.created(URI.create("/sports/" + sports.getId()))
                .body(new SportsCreateResponse(sports.getName()));
    }
}
