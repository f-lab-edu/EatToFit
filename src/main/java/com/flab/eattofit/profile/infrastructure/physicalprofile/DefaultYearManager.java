package com.flab.eattofit.profile.infrastructure.physicalprofile;

import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultYearManager implements YearManager {

    @Override
    public int getCurrentYear() {
        return LocalDate.now().getYear();
    }
}
