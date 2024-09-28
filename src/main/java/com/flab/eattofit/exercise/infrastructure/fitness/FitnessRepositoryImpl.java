package com.flab.eattofit.exercise.infrastructure.fitness;

import com.flab.eattofit.exercise.domain.fitness.Fitness;
import com.flab.eattofit.exercise.domain.fitness.FitnessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FitnessRepositoryImpl implements FitnessRepository {

    private final FitnessJpaRepository fitnessJpaRepository;

    @Override
    public Fitness save(final Fitness fitness) {
        return fitnessJpaRepository.save(fitness);
    }

    @Override
    public boolean existsByName(final String name) {
        return fitnessJpaRepository.existsByName(name);
    }
}
