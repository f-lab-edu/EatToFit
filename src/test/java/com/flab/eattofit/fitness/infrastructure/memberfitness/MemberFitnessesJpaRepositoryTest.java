package com.flab.eattofit.fitness.infrastructure.memberfitness;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import com.flab.eattofit.fitness.infrastructure.memberfitness.MemberFitnessesJpaRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.flab.eattofit.fitness.fixture.memberfitness.MemberFitnessesFixture.회원_피트니스_목록_생성_한개;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MemberFitnessesJpaRepositoryTest {

    @Autowired
    private MemberFitnessesJpaRepository memberFitnessesJpaRepository;

    @Test
    void 회원_피트니스_목록을_생성한다() {
        // given
        MemberFitnesses memberFitnesses = 회원_피트니스_목록_생성_한개();

        // when
        MemberFitnesses savedMemberFitnesses = memberFitnessesJpaRepository.save(memberFitnesses);

        // then
        assertThat(savedMemberFitnesses).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(memberFitnesses);
    }
}
