package com.flab.eattofit.exercise.fixture.fitness.memberfitness;

import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitness;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnesses;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MemberFitnessesFixture {

    public static MemberFitnesses 회원_피트니스_목록_생성_한개() {
        MemberFitness memberFitness = MemberFitness.createWith(1L);

        return MemberFitnesses.builder()
                .memberId(1L)
                .fitnesses(List.of(memberFitness))
                .build();
    }
}
