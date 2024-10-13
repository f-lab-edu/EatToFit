package com.flab.eattofit.fitness.infrastructure.fitness;

import com.flab.eattofit.fitness.domain.fitness.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface FitnessJpaRepository extends JpaRepository<Fitness, Long> {

    Fitness save(Fitness fitness);

    boolean existsByName(String name);

    @Query("select (count(f) = :size) from Fitness f where f.id in :ids")
    boolean existsAllByIds(@Param("ids") Set<Long> ids, @Param("size") long size);

}
