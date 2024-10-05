package com.flab.eattofit.profile.domain.physicalprofile;

import java.util.Optional;

public interface PhysicalProfileRepository {

    PhysicalProfile save(PhysicalProfile physicalProfile);
    Optional<PhysicalProfile> findByMemberId(Long memberId);
}
