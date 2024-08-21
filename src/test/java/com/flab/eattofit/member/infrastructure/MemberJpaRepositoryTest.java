package com.flab.eattofit.member.infrastructure;

import com.flab.eattofit.member.domain.Member;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberRepository;

    @Nested
    class 회원_생성 {

        @Test
        void 회원을_생성한다() {
            // given
            String nickname = "hello";

            // when
            Member member = Member.from(nickname);
            Member savedMember = memberRepository.save(member);

            // then
            assertThat(member).usingRecursiveComparison()
                    .ignoringActualNullFields()
                    .isEqualTo(savedMember);
        }
    }

    @Nested
    class 회원_조회 {

        @Test
        void 회원을_생성하면_id로_조회할_수_있다() {
            // given
            String nickname = "hello";
            Member savedMember = memberRepository.save(Member.from(nickname));

            // when
            Optional<Member> findMember = memberRepository.findById(savedMember.getId());

            // then
            assertSoftly(softly -> {
                softly.assertThat(findMember.isPresent()).isTrue();
                Member member = findMember.get();
                softly.assertThat(member).usingRecursiveComparison()
                        .ignoringActualNullFields()
                        .isEqualTo(savedMember);
            });
        }

        @Test
        void 회원을_닉네임으로_조회한다() {
            // given
            String nickname = "hello";
            memberRepository.save(Member.from(nickname));

            // when
            boolean find = memberRepository.existsByNickname(nickname);

            // then
            assertThat(find).isTrue();
        }
    }
}
