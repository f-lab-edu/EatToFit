package com.flab.eattofit.fitness.domain;

public interface FitnessRepository {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
}
