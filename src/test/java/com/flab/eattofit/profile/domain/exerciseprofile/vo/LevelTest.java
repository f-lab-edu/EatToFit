package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.LevelNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class LevelTest {

    @Nested
    class 운동_레벨_조회 {

        @Test
        void 운동_레벨을_조회한다() {
            // given
            String name = "전문가";

            // when
            Level level = Level.findByName(name);

            // then
            assertSoftly(softly -> {
                softly.assertThat(level).isEqualTo(Level.EXPERT);
                softly.assertThat(level.getName()).isEqualTo(name);
            });
        }

        @Test
        void 없는_운동_레벨_조회_시_예외가_발생한다() {
            // given
            String name = "abc";

            // when & then
            assertThatThrownBy(() -> Level.findByName(name))
                    .isInstanceOf(LevelNotFoundException.class);
        }
    }
}
