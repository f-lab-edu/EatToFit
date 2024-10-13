package com.flab.eattofit.plan.fixture;

import com.flab.eattofit.plan.domain.Exercise;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ExerciseFixture {

    public static List<Exercise> 피트니스_세개_id있음() {
        return List.of(
                스쿼트_id있음(),
                레그프레스_id있음(),
                랫풀다운_id있음()
        );
    }

    private static Exercise 스쿼트_id있음() {
        return Exercise.builder()
                .id(1L)
                .name("스쿼트")
                .repeat(10)
                .expect(BigDecimal.valueOf(100.5))
                .size(12)
                .weight(BigDecimal.valueOf(50))
                .time(null)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private static Exercise 레그프레스_id있음() {
        return Exercise.builder()
                .id(2L)
                .name("레그 프레스")
                .repeat(8)
                .expect(BigDecimal.valueOf(90.1))
                .size(10)
                .weight(BigDecimal.valueOf(100))
                .time(null)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private static Exercise 랫풀다운_id있음() {
        return Exercise.builder()
                .id(3L)
                .name("랫 풀 다운")
                .repeat(10)
                .expect(BigDecimal.valueOf(95.2))
                .size(8)
                .weight(BigDecimal.valueOf(40))
                .time(null)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
