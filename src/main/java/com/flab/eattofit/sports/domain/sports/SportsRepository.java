package com.flab.eattofit.sports.domain.sports;

import java.util.Set;

public interface SportsRepository {

    Sports save(Sports sports);
    boolean existsByName(String name);
    boolean isAllValidIds(Set<Long> ids);
}
