package com.flab.eattofit.exercise.infrastructure.sports;

import com.flab.eattofit.exercise.domain.fitness.Fitness;
import com.flab.eattofit.exercise.domain.sports.Sports;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class SportsJpaRepositoryTest {

    @Autowired
    private SportsJpaRepository sportsJpaRepository;

    @Nested
    class 스포츠_생성 {

        @Test
        void 스포츠를_생성한다() {
            // given
            String name = "축구";
            Sports sports = Sports.from(name);

            // when
            Sports savedSports = sportsJpaRepository.save(sports);

            // then
            assertThat(sports).usingRecursiveComparison()
                    .ignoringActualNullFields()
                    .isEqualTo(savedSports);
        }

        @Test
        void 이미_등록된_스포츠_이름을_사용_시_예외가_발생한다() {
            // given
            String name = "축구";
            sportsJpaRepository.save(Sports.from(name));

            // when & then
            assertThatThrownBy(() -> sportsJpaRepository.save(Sports.from(name)))
                    .isInstanceOf(DataIntegrityViolationException.class);
        }
    }
}
