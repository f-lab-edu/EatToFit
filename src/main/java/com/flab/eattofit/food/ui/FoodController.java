package com.flab.eattofit.food.ui;

import com.flab.eattofit.food.application.FoodService;
import com.flab.eattofit.food.application.dto.FoodCreateRequest;
import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.food.ui.dto.FoodCreateResponse;
import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/foods")
@RestController
public class FoodController {

    private static final String IMAGE_URL = "image_url";

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<FoodCreateResponse> createFood(
            @RequestBody @Valid final FoodCreateRequest request,
            @AuthMember final Long memberId
    ) {
        Food food = foodService.createFood(request, memberId);
        return ResponseEntity.created(URI.create("/foods/" + food.getId()))
                .body(FoodCreateResponse.from(food));
    }

    @GetMapping("/search")
    public ResponseEntity<PredictFoodSearchResponse> search(
            final @AuthMember Long memberId,
            final @RequestParam(value = IMAGE_URL) String imageUrl
    ) {
        PredictFoodSearchResponse response = foodService.foodSearch(memberId, imageUrl);
        return ResponseEntity.ok()
                .body(response);
    }
}
