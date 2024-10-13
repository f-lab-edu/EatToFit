package com.flab.eattofit.fitness.domain.memberfitness;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitness;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberFitnessTest {

    @Test
    void 회원_피트니스를_생성한다() {
        // given
        Long fitnessId = 1L;

        // when
        MemberFitness memberFitness = MemberFitness.createWith(fitnessId);

        // then
        assertThat(memberFitness.getFitnessId()).isEqualTo(fitnessId);
    }
}
