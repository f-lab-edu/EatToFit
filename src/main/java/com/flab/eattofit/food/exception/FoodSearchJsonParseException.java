package com.flab.eattofit.food.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FoodSearchJsonParseException extends GlobalException {

    public FoodSearchJsonParseException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "FOOD_SEARCH_JSON_PARSE", "음식 정보를 파싱하는 데 예외가 발생했습니다.");
    }
}
