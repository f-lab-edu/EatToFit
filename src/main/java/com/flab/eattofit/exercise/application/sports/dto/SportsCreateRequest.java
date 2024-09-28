package com.flab.eattofit.exercise.application.sports.dto;

import jakarta.validation.constraints.NotEmpty;

public record SportsCreateRequest(
        @NotEmpty(message = "등록할 스포츠 이름이 필요합니다.")
        String name
) {
}
