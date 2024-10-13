package com.flab.eattofit.plan.domain;

import com.flab.eattofit.global.domain.BaseEntity;
import com.flab.eattofit.plan.domain.vo.PlanType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Plan extends BaseEntity {

    private static final boolean PLAN_INITIAL_STATUS = false;
    private static final boolean PLAN_DONE_STATUS = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType type;

    @Column(nullable = false)
    private Long memberId;

    @JoinColumn(name = "plan_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Exercise> exercises = new ArrayList<>();

    @Column(nullable = false)
    private Boolean isDone;

    public static Plan createWith(final String type, final Long memberId) {
        return Plan.builder()
                .type(PlanType.findByName(type))
                .memberId(memberId)
                .exercises(new ArrayList<>())
                .isDone(PLAN_INITIAL_STATUS)
                .build();
    }

    public void addExercise(final Exercise exercise) {
        this.exercises.add(exercise);
    }
}
