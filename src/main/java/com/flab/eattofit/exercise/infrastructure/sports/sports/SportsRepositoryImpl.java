package com.flab.eattofit.exercise.infrastructure.sports.sports;

import com.flab.eattofit.exercise.domain.sports.sports.Sports;
import com.flab.eattofit.exercise.domain.sports.sports.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SportsRepositoryImpl implements SportsRepository {

    private final SportsJpaRepository sportsJpaRepository;

    @Override
    public Sports save(final Sports sports) {
        return sportsJpaRepository.save(sports);
    }

    @Override
    public boolean existsByName(final String name) {
        return sportsJpaRepository.existsByName(name);
    }
}
