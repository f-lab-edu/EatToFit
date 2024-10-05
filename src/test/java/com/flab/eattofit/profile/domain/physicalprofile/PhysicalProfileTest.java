package com.flab.eattofit.profile.domain.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Height;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Weight;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Year;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberAgeException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberHeightException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberWeightException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.GenderNotFoundException;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PhysicalProfileTest {

    @Nested
    class 신체_정보_프로필_생성 {

        @Test
        void 신체_정보_프로필을_생성한다() {
            // given
            int birthYear = 2000;
            YearManager yearManager = FixYearManager.from(2024);
            String genderValue = "남성";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            Physical physical = Physical.builder()
                    .birthYear(Year.createWith(birthYear, yearManager))
                    .gender(Gender.findByName(genderValue))
                    .weight(Weight.createWith(weight))
                    .height(Height.createWith(height))
                    .build();

            // when
            PhysicalProfile physicalProfile = PhysicalProfile.createWith(physical, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(physical.getBirthYear().getValue()).isEqualTo(birthYear);
                softly.assertThat(physical.getGender()).isEqualTo(Gender.MALE);
                softly.assertThat(physical.getWeight().getValue()).isEqualTo(weight);
                softly.assertThat(physical.getHeight().getValue()).isEqualTo(height);
                softly.assertThat(physicalProfile.getMemberId()).isEqualTo(memberId);
            });
        }

        @ParameterizedTest(name = "출생년도가 {0}년일 경우")
        @ValueSource(ints = {-1000, 1000, 2020})
        void 최소_출생년도보다_작거나_최대_출생년도보다_크면_예외가_발생한다(final int year) {
            // given
            YearManager yearManager = FixYearManager.from(2024);
            String genderValue = "남성";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            Physical physical = Physical.builder()
                    .birthYear(Year.createWith(year, yearManager))
                    .gender(Gender.findByName(genderValue))
                    .weight(Weight.createWith(weight))
                    .height(Height.createWith(height))
                    .build();

            // when & then
            assertThatThrownBy(() -> PhysicalProfile.createWith(physical, memberId))
                    .isInstanceOf(BadMemberAgeException.class);
        }

        @Test
        void 없는_성별을_선택하면_예외가_발생한다() {
            // given
            int birthYear = 2000;
            YearManager yearManager = FixYearManager.from(2024);
            String genderValue = "abc";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            Physical physical = Physical.builder()
                    .birthYear(Year.createWith(birthYear, yearManager))
                    .gender(Gender.findByName(genderValue))
                    .weight(Weight.createWith(weight))
                    .height(Height.createWith(height))
                    .build();

            // when & then
            assertThatThrownBy(() -> PhysicalProfile.createWith(physical, memberId))
                    .isInstanceOf(GenderNotFoundException.class);
        }

        @ParameterizedTest(name = "몸무게가 {0}kg일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_몸무게보다_작거나_최대_몸무게보다_크면_예외가_발생한다(final Double value) {
            // given
            int birthYear = 2000;
            YearManager yearManager = FixYearManager.from(2024);
            String genderValue = "남성";
            BigDecimal weight = BigDecimal.valueOf(value);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            Physical physical = Physical.builder()
                    .birthYear(Year.createWith(birthYear, yearManager))
                    .gender(Gender.findByName(genderValue))
                    .weight(Weight.createWith(weight))
                    .height(Height.createWith(height))
                    .build();

            // when & then
            assertThatThrownBy(() -> PhysicalProfile.createWith(physical, memberId))
                    .isInstanceOf(BadMemberWeightException.class);
        }

        @ParameterizedTest(name = "신장이 {0}cm일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_신장보다_작거나_최대_신장보다_크면_예외가_발생한다(final Double value) {
            // given
            int birthYear = 2000;
            YearManager yearManager = FixYearManager.from(2024);
            String genderValue = "남성";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(value);
            Long memberId = 1L;

            Physical physical = Physical.builder()
                    .birthYear(Year.createWith(birthYear, yearManager))
                    .gender(Gender.findByName(genderValue))
                    .weight(Weight.createWith(weight))
                    .height(Height.createWith(height))
                    .build();

            // when & then
            assertThatThrownBy(() -> PhysicalProfile.createWith(physical, memberId))
                    .isInstanceOf(BadMemberHeightException.class);
        }
    }
}
