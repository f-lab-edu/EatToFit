package com.flab.eattofit.exercise.infrastructure.fitness;

import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import com.flab.eattofit.exercise.domain.fitness.fitness.FitnessRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Override
    public boolean isAllValidIds(final Set<Long> ids) {
        return ids.stream().allMatch(map::containsKey);
    }
}
