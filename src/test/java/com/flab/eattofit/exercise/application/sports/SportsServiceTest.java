package com.flab.eattofit.exercise.application.sports;

import com.flab.eattofit.exercise.application.sports.dto.SportsCreateRequest;
import com.flab.eattofit.exercise.domain.sports.Sports;
import com.flab.eattofit.exercise.domain.sports.SportsRepository;
import com.flab.eattofit.exercise.exception.sports.exceptions.SportsAlreadyExistedException;
import com.flab.eattofit.exercise.infrastructure.sports.SportsFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class SportsServiceTest {

    private SportsService sportsService;
    private SportsRepository sportsRepository;

    @BeforeEach
    void init() {
        sportsRepository = new SportsFakeRepository();
        sportsService = new SportsService(sportsRepository);
    }

    @Nested
    class 스포츠_등록 {

        @Test
        void 스포츠를_등록한다() {
            // given
            SportsCreateRequest request = new SportsCreateRequest("축구");

            // when
            Sports sports = sportsService.createSports(request);

            // then
            assertSoftly(softly -> {
                softly.assertThat(sports.getId()).isEqualTo(1L);
                softly.assertThat(sports.getName()).isEqualTo("축구");
            });
        }

        @Test
        void 이미_등록된_스포츠_이름을_등록하면_예외가_발생한다() {
            // given
            SportsCreateRequest request = new SportsCreateRequest("축구");
            sportsService.createSports(request);

            // when & then
            assertThatThrownBy(() -> sportsService.createSports(request))
                    .isInstanceOf(SportsAlreadyExistedException.class);
        }
    }
}
