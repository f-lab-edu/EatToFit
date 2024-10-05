package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PhysicalProfileRepositoryImpl implements PhysicalProfileRepository {

    private final PhysicalProfileJpaRepository physicalProfileJpaRepository;

    @Override
    public PhysicalProfile save(final PhysicalProfile physicalProfile) {
        return physicalProfileJpaRepository.save(physicalProfile);
    }

    @Override
    public Optional<PhysicalProfile> findByMemberId(final Long memberId) {
        return physicalProfileJpaRepository.findByMemberId(memberId);
    }
}
