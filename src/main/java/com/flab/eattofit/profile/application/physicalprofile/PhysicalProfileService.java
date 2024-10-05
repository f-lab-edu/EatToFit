package com.flab.eattofit.profile.application.physicalprofile;

import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PhysicalProfileService {

    private final YearManager yearManager;
    private final PhysicalProfileRepository physicalProfileRepository;

    public PhysicalProfile createPhysicalProfile(final PhysicalProfileCreateRequest request, final Long memberId) {
        PhysicalProfile physicalProfile = PhysicalProfile.createWith(
                request.birthYear(),
                yearManager,
                request.gender(),
                request.weight(),
                request.height(),
                memberId);

        return physicalProfileRepository.save(physicalProfile);
    }
}
