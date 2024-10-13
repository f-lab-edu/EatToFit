package com.flab.eattofit.plan.domain;

import com.flab.eattofit.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(exclude = {"id", "createdAt"})
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Exercise extends BaseEntity {

    private static final boolean EXERCISE_INITIAL_STATUS = false;
    private static final boolean EXERCISE_DONE_STATUS = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "repeats")
    private Integer repeat;

    @Column(nullable = false)
    private BigDecimal expect;

    private Integer size;

    private BigDecimal weight;

    private Integer time;

    @Column(nullable = false)
    private Boolean isDone;

    public static Exercise createFitness(
            final String name,
            final Integer repeat,
            final BigDecimal expect,
            final Integer size,
            final BigDecimal weight
    ) {
        return Exercise.builder()
                .name(name)
                .repeat(repeat)
                .expect(expect)
                .size(size)
                .weight(weight)
                .time(null)
                .isDone(EXERCISE_INITIAL_STATUS)
                .build();
    }

    public static Exercise createSports(final String name, final BigDecimal expect, final Integer time) {
        return Exercise.builder()
                .name(name)
                .repeat(null)
                .expect(expect)
                .size(null)
                .weight(null)
                .time(time)
                .isDone(EXERCISE_INITIAL_STATUS)
                .build();
    }
}
