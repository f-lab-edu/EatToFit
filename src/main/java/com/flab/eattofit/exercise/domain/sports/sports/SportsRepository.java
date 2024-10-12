package com.flab.eattofit.exercise.domain.sports.sports;

import java.util.Set;

public interface SportsRepository {

    Sports save(Sports sports);
    boolean existsByName(String name);
    boolean isAllValidIds(Set<Long> ids);
}
