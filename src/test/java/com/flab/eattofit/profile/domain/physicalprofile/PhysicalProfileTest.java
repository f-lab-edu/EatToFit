package com.flab.eattofit.profile.domain.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Height;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Weight;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Year;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PhysicalProfileTest {

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
}
