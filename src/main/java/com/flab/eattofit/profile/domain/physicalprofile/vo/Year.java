package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberAgeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Year {

    private static final int MIN_AGE = 8;
    private static final int MAX_AGE = 100;

    @Column(nullable = false)
    private Integer birthYear;

    private Year(final Integer birthYear) {
        this.birthYear = birthYear;
    }

    public static Year createWith(final Integer birthYear) {
        validateAge(birthYear);
        return new Year(birthYear);
    }

    private static void validateAge(final Integer birthYear) {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int memberAge = currentYear - birthYear;

        if (memberAge < MIN_AGE || memberAge > MAX_AGE) {
            throw new BadMemberAgeException();
        }
    }
}
