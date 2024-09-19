package com.flab.eattofit.fitness.ui;

import com.flab.eattofit.fitness.application.FitnessService;
import com.flab.eattofit.fitness.application.dto.FitnessCreateRequest;
import com.flab.eattofit.fitness.domain.Fitness;
import com.flab.eattofit.fitness.ui.dto.FitnessCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/admin/fitness")
@RestController
public class FitnessController {

    private final FitnessService fitnessService;

    @PostMapping
    public ResponseEntity<FitnessCreateResponse> createFitness(@RequestBody @Valid final FitnessCreateRequest request) {
        Fitness fitness = fitnessService.createFitness(request);
        return ResponseEntity.created(URI.create("/fitness/" + fitness.getId()))
                .body(new FitnessCreateResponse(fitness.getName()));
    }
}
