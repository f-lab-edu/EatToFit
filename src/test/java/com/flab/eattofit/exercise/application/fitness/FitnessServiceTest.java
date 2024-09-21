package com.flab.eattofit.exercise.application.fitness;

import com.flab.eattofit.exercise.application.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.exercise.domain.fitness.Fitness;
import com.flab.eattofit.exercise.domain.fitness.FitnessRepository;
import com.flab.eattofit.exercise.exception.fitness.exceptions.FitnessAlreadyExistedException;
import com.flab.eattofit.exercise.infrastructure.fitness.FitnessFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FitnessServiceTest {

    private FitnessService fitnessService;
    private FitnessRepository fitnessRepository;

    @BeforeEach
    void init() {
        fitnessRepository = new FitnessFakeRepository();
        fitnessService = new FitnessService(fitnessRepository);
    }

    @Nested
    class 피트니스_등록 {

        @Test
        void 피트니스를_등록한다() {
            // given
            FitnessCreateRequest request = new FitnessCreateRequest("덤벨프레스");

            // when
            Fitness fitness = fitnessService.createFitness(request);

            // then
            assertSoftly(softly -> {
                softly.assertThat(fitness.getId()).isEqualTo(1L);
                softly.assertThat(fitness.getName()).isEqualTo("덤벨프레스");
            });
        }

        @Test
        void 이미_등록된_피트니스_이름을_등록하면_예외가_발생한다() {
            // given
            FitnessCreateRequest request = new FitnessCreateRequest("덤벨프레스");
            fitnessService.createFitness(request);

            // when & then
            assertThatThrownBy(() -> fitnessService.createFitness(request))
                    .isInstanceOf(FitnessAlreadyExistedException.class);
        }
    }
}
