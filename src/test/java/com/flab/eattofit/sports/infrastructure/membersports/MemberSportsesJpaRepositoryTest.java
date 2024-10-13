package com.flab.eattofit.sports.infrastructure.membersports;

import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import com.flab.eattofit.sports.infrastructure.membersports.MemberSportsesJpaRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static com.flab.eattofit.sports.fixture.membersports.MemberSportsesFixture.회원_스포츠_목록_생성_한개;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MemberSportsesJpaRepositoryTest {

    @Autowired
    private MemberSportsesJpaRepository memberSportsesJpaRepository;

    @Test
    void 회원_피트니스_목록을_생성한다() {
        // given
        MemberSportses memberSportses = 회원_스포츠_목록_생성_한개();

        // when
        MemberSportses savedMemberSportses = memberSportsesJpaRepository.save(memberSportses);

        // then
        assertThat(savedMemberSportses).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(memberSportses);
    }

    @Test
    void 회원_피트니스_목록을_조회한다() {
        // given
        Long memberId = 1L;
        MemberSportses memberSportses = 회원_스포츠_목록_생성_한개();
        MemberSportses savedMemberSportses = memberSportsesJpaRepository.save(memberSportses);

        // when
        Optional<MemberSportses> foundMemberSportses = memberSportsesJpaRepository.findByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(foundMemberSportses).isPresent();
            softly.assertThat(foundMemberSportses.get()).isEqualTo(savedMemberSportses);
        });
    }
}
