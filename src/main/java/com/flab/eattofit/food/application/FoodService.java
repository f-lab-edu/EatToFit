package com.flab.eattofit.food.application;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodSearchManager foodSearchManager;

    public PredictFoodSearchResponse foodSearch(final Long memberId, final String url) {
        log.info("{} 회원이 {}로 음식 검색 요청", memberId, url);
        return foodSearchManager.search(url);
    }
}
