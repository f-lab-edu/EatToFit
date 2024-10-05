package com.flab.eattofit.profile.fixture.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Height;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Weight;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Year;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PhysicalProfileFixture {

    public static PhysicalProfile 신체_정보_프로필(
            int birthYear,
            String gender,
            BigDecimal weight,
            BigDecimal height,
            Long memberId
    ) {
        YearManager yearManager = FixYearManager.from(2024);
        Physical physical = Physical.builder()
                .birthYear(Year.createWith(birthYear, yearManager))
                .gender(Gender.findByName(gender))
                .weight(Weight.createWith(weight))
                .height(Height.createWith(height))
                .build();

        return PhysicalProfile.createWith(physical, memberId);
    }
}
