package com.flab.eattofit.food.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FoodUnitNotFoundException extends GlobalException  {

    public FoodUnitNotFoundException() {
        super(HttpStatus.NOT_FOUND, "FOOD_UNIT_NOT_FOUND", "이용 가능한 음식 단위 타입이 아닙니다.");
    }
}
