package com.flab.eattofit.profile.ui.physicalprofile.dto;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;

import java.math.BigDecimal;

public record PhysicalProfileCreateResponse(
        Integer birthYear,
        Gender gender,
        BigDecimal weight,
        BigDecimal height,
        Long memberId
) {

    public static PhysicalProfileCreateResponse from(final PhysicalProfile physicalProfile) {
        Physical physical = physicalProfile.getPhysical();
        return new PhysicalProfileCreateResponse(
                physical.getBirthYear().getValue(),
                physical.getGender(),
                physical.getWeight().getValue(),
                physical.getHeight().getValue(),
                physicalProfile.getMemberId()
        );
    }
}
