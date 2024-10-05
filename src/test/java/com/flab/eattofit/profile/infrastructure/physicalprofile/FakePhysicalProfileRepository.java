package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;

import java.util.HashMap;
import java.util.Map;

public class FakePhysicalProfileRepository implements PhysicalProfileRepository {

    private final Map<Long, PhysicalProfile> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public PhysicalProfile save(final PhysicalProfile physicalProfile) {
        PhysicalProfile savedPhysicalProfile = PhysicalProfile.builder()
                .id(id)
                .physical(physicalProfile.getPhysical())
                .memberId(physicalProfile.getMemberId())
                .build();

        map.put(id++, savedPhysicalProfile);
        return savedPhysicalProfile;
    }
}
