package com.flab.eattofit.exercise.domain.fitness.memberfitness;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberFitnessesTest {

    @Test
    void 회원_피트니스_목록을_생성한다() {
        // given
        Long memberId = 1L;

        // when
        MemberFitnesses memberFitnesses = MemberFitnesses.createWithMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(memberFitnesses.getMemberId()).isEqualTo(memberId);
            softly.assertThat(memberFitnesses.getFitnesses()).isEmpty();
        });
    }

    @Test
    void 회원_피트니스_목록에_피트니스를_추가한다() {
        // given
        Long fitnessId = 1L;
        MemberFitness memberFitness = MemberFitness.createWith(fitnessId);

        Long memberId = 1L;
        MemberFitnesses memberFitnesses = MemberFitnesses.createWithMemberId(memberId);

        // when
        memberFitnesses.addFitness(memberFitness);

        // then
        assertThat(memberFitnesses.getFitnesses()).contains(memberFitness);
    }
}
