package com.flab.eattofit.fitness.domain.fitness;

import java.util.Set;

public interface FitnessRepository {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
    boolean isAllValidIds(Set<Long> ids);
}
