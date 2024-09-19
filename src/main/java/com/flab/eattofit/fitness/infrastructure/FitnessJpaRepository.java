package com.flab.eattofit.fitness.infrastructure;

import com.flab.eattofit.fitness.domain.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessJpaRepository extends JpaRepository<Fitness, Long> {

    Fitness save(Fitness fitness);
    boolean existsByName(String name);
}
