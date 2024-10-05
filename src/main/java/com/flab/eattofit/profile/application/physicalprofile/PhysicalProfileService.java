package com.flab.eattofit.profile.application.physicalprofile;

import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Height;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Weight;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Year;
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
        Physical physical = Physical.builder()
                .birthYear(Year.createWith(request.birthYear(), yearManager))
                .gender(Gender.findByName(request.gender()))
                .weight(Weight.createWith(request.weight()))
                .height(Height.createWith(request.height()))
                .build();
        PhysicalProfile physicalProfile = PhysicalProfile.createWith(physical, memberId);

        return physicalProfileRepository.save(physicalProfile);
    }
}
