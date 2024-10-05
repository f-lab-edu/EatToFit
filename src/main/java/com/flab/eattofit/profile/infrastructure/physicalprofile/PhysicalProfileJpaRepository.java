package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicalProfileJpaRepository extends JpaRepository<PhysicalProfile, Long> {

    PhysicalProfile save(PhysicalProfile physicalProfile);
    Optional<PhysicalProfile> findByMemberId(Long memberId);
}
