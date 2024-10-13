package com.flab.eattofit.fitness.application.memberfitness;

import com.flab.eattofit.fitness.application.fitness.FitnessService;
import com.flab.eattofit.fitness.application.fitness.event.events.MemberFitnessesSubmittedEvent;
import com.flab.eattofit.fitness.application.memberfitness.dto.MemberFitnessesRequest;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnessesRepository;
import com.flab.eattofit.fitness.exception.fitness.exceptions.FitnessNotFoundException;
import com.flab.eattofit.fitness.infrastructure.memberfitness.MemberFitnessesFakeRepository;
import com.flab.eattofit.global.config.event.Events;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import static com.flab.eattofit.fitness.fixture.memberfitness.MemberFitnessesRequestFixture.회원_선호_피트니스_등록_두개_요청;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberFitnessesServiceTest {

    private MemberFitnessesService memberFitnessesService;
    private MemberFitnessesRepository memberFitnessesRepository;

    @Mock
    private FitnessService fitnessService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        memberFitnessesRepository = new MemberFitnessesFakeRepository();
        memberFitnessesService = new MemberFitnessesService(memberFitnessesRepository);
        Events.setPublisher(eventPublisher);
    }

    @Nested
    class 회원_선호_피트니스_등록 {

        @Test
        void 회원_선호_피트니스를_등록한다() {
            // given
            MemberFitnessesRequest request = 회원_선호_피트니스_등록_두개_요청();
            Long memberId = 1L;

            doNothing().when(fitnessService).validateFitnessIds(anySet());

            // when
            memberFitnessesService.submitMemberFitnesses(request, memberId);
            Optional<MemberFitnesses> memberFitnesses = memberFitnessesRepository.findByMemberId(memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThatCode(() -> verify(eventPublisher, times(1))
                        .publishEvent(any(MemberFitnessesSubmittedEvent.class))).doesNotThrowAnyException();
                softly.assertThat(memberFitnesses).isPresent();
                softly.assertThat(memberFitnesses.get().getFitnesses()).hasSize(2);
            });
        }

        @Test
        void 없는_피트니스를_등록하려_하면_예외가_발생한다() {
            // given
            MemberFitnessesRequest request = 회원_선호_피트니스_등록_두개_요청();
            Long memberId = 1L;

            doThrow(new FitnessNotFoundException()).when(eventPublisher).publishEvent(any(MemberFitnessesSubmittedEvent.class));

            // TODO: assertThatCode(...).publishEvent(...).hasCauseInstanceOf(FitnessNotFoundException.class) 검증 구현 방법 찾기
            // when & then
            assertSoftly(softly -> {
                softly.assertThatThrownBy(() -> memberFitnessesService.submitMemberFitnesses(request, memberId))
                        .isInstanceOf(FitnessNotFoundException.class);
                /* softly.assertThatCode(() -> verify(eventPublisher, times(1))
                        .when(memberFitnessesService.submitMemberFitnesses(request, memberId))
                        .publishEvent(any(MemberFitnessesSubmittedEvent.class))).hasCauseInstanceOf(FitnessNotFoundException.class); */
            });
        }
    }
}
