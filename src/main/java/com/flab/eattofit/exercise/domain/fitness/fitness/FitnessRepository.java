package com.flab.eattofit.exercise.domain.fitness.fitness;

import java.util.Set;

public interface FitnessRepository {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
    boolean isAllValidIds(Set<Long> ids);
}
