package com.flab.eattofit.exercise.application.fitness;

import com.flab.eattofit.exercise.application.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import com.flab.eattofit.exercise.domain.fitness.fitness.FitnessRepository;
import com.flab.eattofit.exercise.exception.fitness.exceptions.FitnessAlreadyExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class FitnessService {

    private final FitnessRepository fitnessRepository;

    public Fitness createFitness(final FitnessCreateRequest request) {
        validateFitnessNameUsed(request);
        return fitnessRepository.save(Fitness.from(request.name()));
    }

    private void validateFitnessNameUsed(final FitnessCreateRequest request) {
        if (fitnessRepository.existsByName(request.name())) {
            throw new FitnessAlreadyExistedException();
        }
    }

}
