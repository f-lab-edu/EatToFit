package com.flab.eattofit.exercise.infrastructure.sports.sports;

import com.flab.eattofit.exercise.domain.sports.sports.Sports;
import com.flab.eattofit.exercise.domain.sports.sports.SportsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SportsFakeRepository implements SportsRepository {

    private final Map<Long, Sports> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public Sports save(final Sports sports) {
        Sports savedSports = Sports.builder()
                .id(id)
                .name(sports.getName())
                .build();
        map.put(id++, savedSports);
        return savedSports;
    }

    @Override
    public boolean existsByName(final String name) {
        return map.values()
                .stream()
                .anyMatch(sports -> name.equals(sports.getName()));
    }

    @Override
    public boolean isAllValidIds(final Set<Long> ids) {
        return ids.stream().allMatch(map::containsKey);
    }
}
