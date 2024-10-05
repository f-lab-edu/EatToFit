package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberAgeException;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class YearTest {

    @Nested
    class 출생년도_생성 {

        @Test
        void 출생년도를_생성한다() {
            // given
            Integer birthYear = 2000;
            YearManager yearManager = FixYearManager.from(2024);

            // when
            Year year = Year.createWith(birthYear, yearManager);

            // then
            assertThat(year.getBirthYear()).isEqualTo(birthYear);
        }

        @ParameterizedTest(name = "출생년도가 {0}년일 경우")
        @ValueSource(ints = {-1000, 1000, 2020})
        void 최소_출생년도보다_작거나_최대_출생년도보다_크면_예외가_발생한다(final int year) {
            // given
            YearManager yearManager = FixYearManager.from(2024);

            // when & then
            assertThatThrownBy(() -> Year.createWith(year, yearManager))
                    .isInstanceOf(BadMemberAgeException.class);
        }
    }
}
