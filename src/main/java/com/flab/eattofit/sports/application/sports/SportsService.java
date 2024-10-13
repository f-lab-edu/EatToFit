package com.flab.eattofit.sports.application.sports;

import com.flab.eattofit.sports.application.sports.dto.SportsCreateRequest;
import com.flab.eattofit.sports.domain.sports.Sports;
import com.flab.eattofit.sports.domain.sports.SportsRepository;
import com.flab.eattofit.exercise.exception.sports.sports.exceptions.SportsAlreadyExistedException;
import com.flab.eattofit.exercise.exception.sports.sports.exceptions.SportsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class SportsService {

    private final SportsRepository sportsRepository;

    public Sports createSports(final SportsCreateRequest request) {
        validateSportsNameUsed(request);
        return sportsRepository.save(Sports.from(request.name()));
    }

    private void validateSportsNameUsed(final SportsCreateRequest request) {
        if (sportsRepository.existsByName(request.name())) {
            throw new SportsAlreadyExistedException();
        }
    }

    public void validateSportsIds(final Set<Long> sportsIds) {
        if (!sportsRepository.isAllValidIds(sportsIds)) {
            throw new SportsNotFoundException();
        }
    }
}
