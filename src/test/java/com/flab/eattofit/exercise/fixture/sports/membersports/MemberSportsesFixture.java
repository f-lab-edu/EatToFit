package com.flab.eattofit.exercise.fixture.sports.membersports;

import com.flab.eattofit.exercise.domain.sports.membersports.MemberSports;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportses;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MemberSportsesFixture {

    public static MemberSportses 회원_스포츠_목록_생성_한개() {
        MemberSports memberSports = MemberSports.createWith(1L);

        return MemberSportses.builder()
                .memberId(1L)
                .sports(List.of(memberSports))
                .build();
    }
}
