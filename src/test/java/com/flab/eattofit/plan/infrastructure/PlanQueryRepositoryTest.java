package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import com.flab.eattofit.exercise.domain.fitness.fitness.FitnessRepository;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnessesRepository;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportsesRepository;
import com.flab.eattofit.exercise.domain.sports.sports.Sports;
import com.flab.eattofit.exercise.domain.sports.sports.SportsRepository;
import com.flab.eattofit.helper.IntegrationHelper;
import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;
import com.flab.eattofit.plan.infrastructure.dto.MemberProfileResponse;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import static com.flab.eattofit.exercise.fixture.fitness.memberfitness.MemberFitnessesFixture.회원_피트니스_목록_생성_한개;
import static com.flab.eattofit.exercise.fixture.sports.membersports.MemberSportsesFixture.회원_스포츠_목록_생성_한개;
import static com.flab.eattofit.profile.fixture.exerciseprofile.ExerciseProfileFixture.운동_정보_프로필;
import static com.flab.eattofit.profile.fixture.physicalprofile.PhysicalProfileFixture.신체_정보_프로필;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PlanQueryRepositoryTest extends IntegrationHelper {

    @Autowired
    private PlanQueryRepository planQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ExerciseProfileRepository exerciseProfileRepository;

    @Autowired
    private PhysicalProfileRepository physicalProfileRepository;

    @Autowired
    private FitnessRepository fitnessRepository;

    @Autowired
    private SportsRepository sportsRepository;

    @Autowired
    private MemberFitnessesRepository memberFitnessesRepository;

    @Autowired
    private MemberSportsesRepository memberSportsesRepository;

    @Test
    void 플랜_생성에_필요한_정보를_조회한다() {
        // given
        BigDecimal kcal = BigDecimal.valueOf(270);
        Member member = memberRepository.save(Member.of("test@gmail.com", "test"));
        exerciseProfileRepository.save(운동_정보_프로필("3개월 미만", "주 3회", "근비대", "중급자", member.getId()));

        BigDecimal weight = BigDecimal.valueOf(67);
        BigDecimal height = BigDecimal.valueOf(172);
        physicalProfileRepository.save(신체_정보_프로필(2000, "남성", weight, height, member.getId()));

        fitnessRepository.save(Fitness.from("덤벨 프레스"));
        sportsRepository.save(Sports.from("축구"));
        memberFitnessesRepository.save(회원_피트니스_목록_생성_한개());
        memberSportsesRepository.save(회원_스포츠_목록_생성_한개());

        MemberProfileResponse expectedMemberProfile = new MemberProfileResponse(
                "UNDER_THREE_MONTH",
                "THREE",
                "INCREASE_MUSCLE",
                "INTERMEDIATE",
                2000,
                "MALE",
                weight.setScale(2, RoundingMode.DOWN),
                height.setScale(2, RoundingMode.DOWN)
        );

        // when
        PredictPlanSearchRequest predictPlanSearchRequest = planQueryRepository.getPredictPlanSearchRequest(kcal, member.getId());

        // then
        assertSoftly(softly -> {
            softly.assertThat(predictPlanSearchRequest.kcal()).isEqualTo(kcal);
            softly.assertThat(predictPlanSearchRequest.memberProfile()).isEqualTo(expectedMemberProfile);
            softly.assertThat(predictPlanSearchRequest.fitness()).isEqualTo(List.of("덤벨 프레스"));
            softly.assertThat(predictPlanSearchRequest.sports()).isEqualTo(List.of("축구"));
        });
    }
}
