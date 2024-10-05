package com.flab.eattofit.profile.domain.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.vo.Physical;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class PhysicalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Physical physical;

    @Column(nullable = false)
    private Long memberId;

    private PhysicalProfile(
            final Physical physical,
            final Long memberId
    ) {
        this.physical = physical;
        this.memberId = memberId;
    }

    public static PhysicalProfile createWith(
            final Physical physical,
            final Long memberId
    ) {
        return new PhysicalProfile(physical, memberId);
    }
}
