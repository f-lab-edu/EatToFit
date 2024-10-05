package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberWeightException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Weight {

    private static final BigDecimal MIN_WEIGHT = BigDecimal.valueOf(15);
    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(200);

    @Column(nullable = false, name = "weight")
    private BigDecimal value;

    private Weight(final BigDecimal value) {
        this.value = value;
    }

    public static Weight createWith(final BigDecimal weight) {
        validateWeight(weight);
        return new Weight(weight);
    }

    private static void validateWeight(final BigDecimal weight) {
        if (weight.compareTo(MIN_WEIGHT) < 0 || weight.compareTo(MAX_WEIGHT) > 0) {
            throw new BadMemberWeightException();
        }
    }
}
