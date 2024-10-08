package com.flab.eattofit.exercise.domain.sports.sports;

public interface SportsRepository {

    Sports save(Sports sports);
    boolean existsByName(String name);
}
