package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import static com.flab.eattofit.profile.fixture.physicalprofile.PhysicalProfileFixture.신체_정보_프로필;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class PhysicalProfileJpaRepositoryTest {

    @Autowired
    private PhysicalProfileJpaRepository physicalProfileJpaRepository;

    @Test
    void 회원_신체_정보_프로필을_저장한다() {
        // given
        int birthYear = 2000;
        String gender = "남성";
        BigDecimal weight = BigDecimal.valueOf(67.2);
        BigDecimal height = BigDecimal.valueOf(171.2);
        Long memberId = 1L;

        PhysicalProfile physicalProfile = 신체_정보_프로필(birthYear, gender, weight, height, memberId);

        // when
        PhysicalProfile savedPhysicalProfile = physicalProfileJpaRepository.save(physicalProfile);

        // then
        assertThat(savedPhysicalProfile).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(physicalProfile);
    }
}
