package com.flab.eattofit.sports.infrastructure.sports;

import com.flab.eattofit.sports.domain.sports.Sports;
import com.flab.eattofit.sports.domain.sports.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;

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

    @Override
    public boolean isAllValidIds(Set<Long> ids) {
        return sportsJpaRepository.isAllValidIds(ids, ids.size());
    }
}
