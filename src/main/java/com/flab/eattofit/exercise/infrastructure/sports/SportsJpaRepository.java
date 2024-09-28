package com.flab.eattofit.exercise.infrastructure.sports;

import com.flab.eattofit.exercise.domain.sports.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsJpaRepository extends JpaRepository<Sports, Long> {

    Sports save(Sports sports);
    boolean existsByName(String name);
}
