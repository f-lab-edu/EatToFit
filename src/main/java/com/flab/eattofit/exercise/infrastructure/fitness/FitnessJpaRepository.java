package com.flab.eattofit.exercise.infrastructure.fitness;

import com.flab.eattofit.exercise.domain.fitness.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessJpaRepository extends JpaRepository<Fitness, Long> {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
}
