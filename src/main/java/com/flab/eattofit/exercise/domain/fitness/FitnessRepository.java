package com.flab.eattofit.exercise.domain.fitness;

public interface FitnessRepository {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
}
