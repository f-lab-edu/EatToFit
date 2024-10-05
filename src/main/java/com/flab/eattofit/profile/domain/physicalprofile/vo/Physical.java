package com.flab.eattofit.profile.domain.physicalprofile.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Physical {

    @Embedded
    private Year birthYear;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Embedded
    private Weight weight;

    @Embedded
    private Height height;

    private Physical(
            final Year birthYear,
            final Gender gender,
            final Weight weight,
            final Height height
    ) {
        this.birthYear = birthYear;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public static Physical createWith(
            final Integer birthYear,
            final String gender,
            final BigDecimal weight,
            final BigDecimal height
    ) {
        return new Physical(Year.createWith(birthYear), Gender.findByName(gender), Weight.createWith(weight), Height.createWith(height));
    }
}
