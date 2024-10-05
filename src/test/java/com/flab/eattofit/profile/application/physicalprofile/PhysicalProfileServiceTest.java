package com.flab.eattofit.profile.application.physicalprofile;

import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FakePhysicalProfileRepository;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static com.flab.eattofit.profile.fixture.physicalprofile.PhysicalProfileFixture.신체_정보_프로필;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PhysicalProfileServiceTest {

    private PhysicalProfileService physicalProfileService;
    private YearManager yearManager;
    private PhysicalProfileRepository physicalProfileRepository;

    @BeforeEach
    void init() {
        yearManager = FixYearManager.from(2024);
        physicalProfileRepository = new FakePhysicalProfileRepository();
        physicalProfileService = new PhysicalProfileService(yearManager, physicalProfileRepository);
    }

    @Test
    void 회원_신체_정보_프로필을_저장한다() {
        // given
        int birthYear = 2000;
        String gender = "남성";
        BigDecimal weight = BigDecimal.valueOf(67.2);
        BigDecimal height = BigDecimal.valueOf(171.2);
        Long memberId = 1L;

        PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(birthYear, gender, weight, height);
        PhysicalProfile expectedPhysicalProfile = 신체_정보_프로필(birthYear, gender, weight, height, memberId);

        // when
        PhysicalProfile physicalProfile = physicalProfileService.createPhysicalProfile(request, memberId);

        // then
        assertThat(physicalProfile).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedPhysicalProfile);
    }
}
