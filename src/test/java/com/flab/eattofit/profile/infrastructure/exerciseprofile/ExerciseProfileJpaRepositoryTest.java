package com.flab.eattofit.profile.infrastructure.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class ExerciseProfileJpaRepositoryTest {

    @Autowired
    private ExerciseProfileJpaRepository exerciseProfileJpaRepository;

    @Test
    void 회원_운동_정보_프로필을_저장한다() {
        // given
        Long memberId = 1L;
        ExerciseProfile exerciseProfile = ExerciseProfile.createWith("처음", "주 1회", "근비대", "초보자", memberId);

        // when
        ExerciseProfile savedProfile = exerciseProfileJpaRepository.save(exerciseProfile);

        // then
        assertThat(savedProfile).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(exerciseProfile);
    }

    @Test
    void 회원_운동_정보_프로필이_저장되면_회원_id로_조회_시_존재한다() {
        // given
        Long memberId = 1L;
        ExerciseProfile exerciseProfile = ExerciseProfile.createWith("처음", "주 1회", "근비대", "초보자", memberId);
        exerciseProfileJpaRepository.save(exerciseProfile);

        // when
        boolean find = exerciseProfileJpaRepository.existsByMemberId(memberId);

        // then
        assertThat(find).isTrue();
    }
}
