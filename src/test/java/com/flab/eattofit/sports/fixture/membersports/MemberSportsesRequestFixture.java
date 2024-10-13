package com.flab.eattofit.sports.fixture.membersports;

import com.flab.eattofit.sports.application.membersports.dto.MemberSportsesRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MemberSportsesRequestFixture {

    public static MemberSportsesRequest 회원_선호_스포츠_등록_두개_요청() {
        return new MemberSportsesRequest(List.of(1L, 2L));
    }
}
