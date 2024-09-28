package com.flab.eattofit.exercise.ui.fitness;

import com.flab.eattofit.exercise.application.fitness.FitnessService;
import com.flab.eattofit.exercise.application.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.exercise.domain.fitness.Fitness;
import com.flab.eattofit.exercise.ui.fitness.dto.FitnessCreateResponse;
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
