package com.flab.eattofit.sports.domain.membersports;

import com.flab.eattofit.sports.domain.membersports.MemberSports;
import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberSportsesTest {

    @Test
    void 회원_스포츠_목록을_생성한다() {
        // given
        Long memberId = 1L;

        // when
        MemberSportses memberSportses = MemberSportses.createWithMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(memberSportses.getMemberId()).isEqualTo(memberId);
            softly.assertThat(memberSportses.getSports()).isEmpty();
        });
    }

    @Test
    void 회원_스포츠_목록에_스포츠를_추가한다() {
        // given
        Long sportsId = 1L;
        MemberSports memberSports = MemberSports.createWith(sportsId);

        Long memberId = 1L;
        MemberSportses memberSportses = MemberSportses.createWithMemberId(memberId);

        // when
        memberSportses.addSports(memberSports);

        // then
        assertThat(memberSportses.getSports()).contains(memberSports);
    }
}
