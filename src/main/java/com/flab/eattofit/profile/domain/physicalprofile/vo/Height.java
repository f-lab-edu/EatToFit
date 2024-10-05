package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberHeightException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Height {

    private static final BigDecimal MIN_HEIGHT = BigDecimal.valueOf(100);
    private static final BigDecimal MAX_HEIGHT = BigDecimal.valueOf(250);

    @Column(nullable = false, name = "height")
    private BigDecimal value;

    private Height(final BigDecimal value) {
        this.value = value;
    }

    public static Height createWith(final BigDecimal height) {
        validateHeight(height);
        return new Height(height);
    }

    private static void validateHeight(final BigDecimal height) {
        if (height.compareTo(MIN_HEIGHT) < 0 || height.compareTo(MAX_HEIGHT) > 0) {
            throw new BadMemberHeightException();
        }
    }
}
