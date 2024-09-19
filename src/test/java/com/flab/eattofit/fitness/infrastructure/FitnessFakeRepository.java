package com.flab.eattofit.fitness.infrastructure;

import com.flab.eattofit.fitness.domain.Fitness;
import com.flab.eattofit.fitness.domain.FitnessRepository;

import java.util.HashMap;
import java.util.Map;

public class FitnessFakeRepository implements FitnessRepository {

    private final Map<Long, Fitness> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public Fitness save(final Fitness fitness) {
        Fitness savedFitness = Fitness.builder()
                .id(id)
                .name(fitness.getName())
                .build();

        map.put(id++, savedFitness);
        return savedFitness;
    }

    @Override
    public boolean existsByName(final String name) {
        return map.values()
                .stream()
                .anyMatch(fitness -> name.equals(fitness.getName()));
    }
}
