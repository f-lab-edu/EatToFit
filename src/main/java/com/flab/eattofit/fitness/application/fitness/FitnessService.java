package com.flab.eattofit.fitness.application.fitness;

import com.flab.eattofit.fitness.application.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.fitness.domain.fitness.Fitness;
import com.flab.eattofit.fitness.domain.fitness.FitnessRepository;
import com.flab.eattofit.fitness.exception.fitness.exceptions.FitnessAlreadyExistedException;
import com.flab.eattofit.fitness.exception.fitness.exceptions.FitnessNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

    public void validateFitnessIds(final Set<Long> fitnessIds) {
        if (!fitnessRepository.isAllValidIds(fitnessIds)) {
            throw new FitnessNotFoundException();
        }
    }
}
