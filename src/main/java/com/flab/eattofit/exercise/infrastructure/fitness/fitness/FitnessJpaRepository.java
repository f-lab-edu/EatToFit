package com.flab.eattofit.exercise.infrastructure.fitness.fitness;

import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface FitnessJpaRepository extends JpaRepository<Fitness, Long> {

    Fitness save(Fitness fitness);

    boolean existsByName(String name);

    @Query("select count(f) =: size from Fitness f where f.id in :ids")
    boolean existsAllByNames(@Param("names") Set<Long> ids, @Param("size") long size);
}
