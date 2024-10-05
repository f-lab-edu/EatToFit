package com.flab.eattofit.profile.fixture.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
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
        return PhysicalProfile.createWith(birthYear, yearManager, gender, weight, height, memberId);
    }
}
