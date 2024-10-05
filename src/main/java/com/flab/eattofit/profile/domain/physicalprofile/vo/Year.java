package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberAgeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Year {

    private static final int MIN_AGE = 8;
    private static final int MAX_AGE = 100;

    @Column(nullable = false)
    private Integer value;

    private Year(final Integer value) {
        this.value = value;
    }

    public static Year createWith(final Integer birthYear, final YearManager yearManager) {
        validateAge(birthYear, yearManager);
        return new Year(birthYear);
    }

    private static void validateAge(final Integer birthYear, final YearManager yearManager) {
        int currentYear = yearManager.getCurrentYear();
        int memberAge = currentYear - birthYear;

        if (memberAge < MIN_AGE || memberAge > MAX_AGE) {
            throw new BadMemberAgeException();
        }
    }
}
