package com.flab.eattofit.sports.application.membersports.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MemberSportsesRequest(
        @NotNull(message = "선호할 스포츠 id 목록이 있어야 합니다.")
        List<Long> sportses
) {
}
