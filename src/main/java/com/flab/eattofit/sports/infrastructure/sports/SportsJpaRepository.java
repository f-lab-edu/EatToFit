package com.flab.eattofit.sports.infrastructure.sports;

import com.flab.eattofit.sports.domain.sports.Sports;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SportsJpaRepository extends JpaRepository<Sports, Long> {

    Sports save(Sports sports);

    boolean existsByName(String name);

    @Query("select (count(s) = :size) from Sports s where s.id in :ids")
    boolean isAllValidIds(@Param("ids") Set<Long> ids, @Param("size") long size);
}
