package com.flab.eattofit.food.application;

import com.flab.eattofit.food.domain.FoodRepository;
import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.exception.BadImageUrlException;
import com.flab.eattofit.food.exception.FoodSearchJsonParseException;
import com.flab.eattofit.food.infrastructure.FakeFoodRepository;
import com.flab.eattofit.food.infrastructure.dto.FoodSearchResponse;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.eattofit.food.fixture.FoodSearchResponseFixture.음식_아님_빈_목록;
import static com.flab.eattofit.food.fixture.FoodSearchResponseFixture.음식_응답_비빔밥;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodSearchManager foodSearchManager;

    private FoodRepository foodRepository;

    private FoodService foodService;

    @BeforeEach
    void init() {
        foodRepository = new FakeFoodRepository();
        foodService = new FoodService(foodRepository, foodSearchManager);
    }

    @Nested
    class 음식_검색 {

        @Test
        void 음식을_검색한다() {
            // given
            Long memberId = 1L;
            String url = "www.food.com/bibimbap.jpg";
            when(foodSearchManager.search(url)).thenReturn(new PredictFoodSearchResponse(음식_응답_비빔밥()));

            // when
            PredictFoodSearchResponse response = foodService.foodSearch(memberId, url);

            // then
            assertSoftly(softly -> {
                softly.assertThat(response.predictFoods()).isNotEmpty();
                softly.assertThat(response.predictFoods()).extracting(FoodSearchResponse::name)
                        .containsExactly(
                                "비빔밥",
                                "산채비빔밥",
                                "돌솥비빔밥"
                        );
            });
        }

        @Test
        void 사진이_음식이_아니면_빈_목록을_반환한다() {
            // given
            Long memberId = 1L;
            String url = "www.image.com/car.jpg";
            when(foodSearchManager.search(url)).thenReturn(new PredictFoodSearchResponse(음식_아님_빈_목록()));

            // when
            PredictFoodSearchResponse response = foodService.foodSearch(memberId, url);

            // then
            assertThat(response.predictFoods()).isEmpty();
        }

        @Test
        void 사진_경로가_잘못되면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            String url = "abcd";
            when(foodSearchManager.search(url)).thenThrow(BadImageUrlException.class);

            // when & then
            assertThatThrownBy(() -> foodService.foodSearch(memberId, url))
                    .isInstanceOf(BadImageUrlException.class);
        }

        @Test
        void 반환_형태가_다르면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            String url = "www.food.com/burger.jpg";
            when(foodSearchManager.search(url)).thenThrow(FoodSearchJsonParseException.class);

            // when & then
            assertThatThrownBy(() -> foodService.foodSearch(memberId, url))
                    .isInstanceOf(FoodSearchJsonParseException.class);
        }
    }
}
