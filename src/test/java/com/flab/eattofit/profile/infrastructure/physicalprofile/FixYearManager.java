package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.YearManager;

public class FixYearManager implements YearManager {

    private final int year;

    private FixYearManager(final int year) {
        this.year = year;
    }

    public static FixYearManager from(final int year) {
        return new FixYearManager(year);
    }

    @Override
    public int getCurrentYear() {
        return year;
    }
}
