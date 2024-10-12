package com.flab.eattofit.exercise.domain.sports.membersports;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberSportsTest {

    @Test
    void 회원_스포츠를_생성한다() {
        // given
        Long sportsId = 1L;

        // when
        MemberSports memberSports = MemberSports.createWith(sportsId);

        // then
        assertThat(memberSports.getSportsId()).isEqualTo(sportsId);
    }
}
