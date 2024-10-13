package com.flab.eattofit.fitness.fixture.memberfitness;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitness;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
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
