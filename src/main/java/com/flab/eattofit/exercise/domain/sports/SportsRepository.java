package com.flab.eattofit.exercise.domain.sports;

public interface SportsRepository {

    Sports save(Sports sports);
    boolean existsByName(String name);
}
