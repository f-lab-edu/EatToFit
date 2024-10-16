package com.flab.eattofit.fitness.infrastructure.fitness;

import com.flab.eattofit.fitness.domain.fitness.Fitness;
import com.flab.eattofit.fitness.domain.fitness.FitnessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;

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

    @Override
    public boolean isAllValidIds(final Set<Long> names) {
        return fitnessJpaRepository.existsAllByIds(names, names.size());
    }
}
