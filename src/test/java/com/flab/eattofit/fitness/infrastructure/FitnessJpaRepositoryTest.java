package com.flab.eattofit.fitness.infrastructure;

import com.flab.eattofit.fitness.domain.Fitness;
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
class FitnessJpaRepositoryTest {

    @Autowired
    private FitnessJpaRepository fitnessJpaRepository;

    @Nested
    class 피트니스_생성 {

        @Test
        void 피트니스를_생성한다() {
            // given
            String name = "덤벨프레스";
            Fitness fitness = Fitness.from(name);

            // when
            Fitness savedFitness = fitnessJpaRepository.save(fitness);

            // then
            assertThat(fitness).usingRecursiveComparison()
                    .ignoringActualNullFields()
                    .isEqualTo(savedFitness);
        }

        @Test
        void 이미_등록된_피트니스_이름을_사용_시_예외가_발생한다() {
            // given
            String name = "덤벨프레스";
            fitnessJpaRepository.save(Fitness.from(name));

            // when & then
            assertThatThrownBy(() -> fitnessJpaRepository.save(Fitness.from(name)))
                    .isInstanceOf(DataIntegrityViolationException.class);
        }
    }
}
