package com.flab.eattofit.exercise.application.sports.sports;

import com.flab.eattofit.exercise.application.sports.sports.dto.SportsCreateRequest;
import com.flab.eattofit.exercise.domain.sports.sports.Sports;
import com.flab.eattofit.exercise.domain.sports.sports.SportsRepository;
import com.flab.eattofit.exercise.exception.sports.sports.exceptions.SportsAlreadyExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
