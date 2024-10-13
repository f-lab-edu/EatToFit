package com.flab.eattofit.plan.fixture;

import com.flab.eattofit.plan.domain.Plan;
import com.flab.eattofit.plan.domain.vo.PlanType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.time.LocalDateTime;
import static com.flab.eattofit.plan.fixture.ExerciseFixture.피트니스_세개_id있음;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlanFixture {

    public static Plan 플랜_피트니스_세개(final Long memberId) {
        return Plan.builder()
                .id(1L)
                .type(PlanType.FITNESS)
                .memberId(memberId)
                .exercises(피트니스_세개_id있음())
                .isDone(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
