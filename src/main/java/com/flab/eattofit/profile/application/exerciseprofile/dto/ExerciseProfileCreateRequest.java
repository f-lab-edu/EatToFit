package com.flab.eattofit.profile.application.exerciseprofile.dto;

import jakarta.validation.constraints.NotNull;

public record ExerciseProfileCreateRequest(
        @NotNull(message = "운동 경력이 필요합니다.")
        String experience,

        @NotNull(message = "운동 빈도가 필요합니다.")
        String frequency,

        @NotNull(message = "운동 목표가 필요합니다.")
        String goal,

        @NotNull(message = "운동 레벨이 필요합니다.")
        String level
) {
}
