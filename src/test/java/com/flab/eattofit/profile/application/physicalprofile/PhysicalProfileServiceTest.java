package com.flab.eattofit.profile.application.physicalprofile;

import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberAgeException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberHeightException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberWeightException;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.GenderNotFoundException;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FakePhysicalProfileRepository;
import com.flab.eattofit.profile.infrastructure.physicalprofile.FixYearManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import static com.flab.eattofit.profile.fixture.physicalprofile.PhysicalProfileFixture.신체_정보_프로필;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Nested
    class 회원_신체_정보_프로필_저장 {

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

        @ParameterizedTest(name = "출생년도가 {0}년일 경우")
        @ValueSource(ints = {-1000, 1000, 2020})
        void 최소_출생년도보다_작거나_최대_출생년도보다_크면_예외가_발생한다(final int year) {
            // given
            String gender = "남성";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(year, gender, weight, height);

            // when & then
            assertThatThrownBy(() -> physicalProfileService.createPhysicalProfile(request, memberId))
                    .isInstanceOf(BadMemberAgeException.class);
        }

        @Test
        void 없는_성별을_선택하면_예외가_발생한다() {
            // given
            int birthYear = 2000;
            String gender = "abc";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(birthYear, gender, weight, height);

            // when & then
            assertThatThrownBy(() -> physicalProfileService.createPhysicalProfile(request, memberId))
                    .isInstanceOf(GenderNotFoundException.class);
        }

        @ParameterizedTest(name = "몸무게가 {0}kg일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_몸무게보다_작거나_최대_몸무게보다_크면_예외가_발생한다(final Double value) {
            // given
            int birthYear = 2000;
            String gender = "남성";
            BigDecimal weight = BigDecimal.valueOf(value);
            BigDecimal height = BigDecimal.valueOf(171.2);
            Long memberId = 1L;

            PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(birthYear, gender, weight, height);

            // when & then
            assertThatThrownBy(() -> physicalProfileService.createPhysicalProfile(request, memberId))
                    .isInstanceOf(BadMemberWeightException.class);
        }

        @ParameterizedTest(name = "신장이 {0}cm일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_신장보다_작거나_최대_신장보다_크면_예외가_발생한다(final Double value) {
            // given
            int birthYear = 2000;
            String gender = "남성";
            BigDecimal weight = BigDecimal.valueOf(67.2);
            BigDecimal height = BigDecimal.valueOf(value);
            Long memberId = 1L;

            PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(birthYear, gender, weight, height);

            // when & then
            assertThatThrownBy(() -> physicalProfileService.createPhysicalProfile(request, memberId))
                    .isInstanceOf(BadMemberHeightException.class);
        }
    }
}
