package com.flab.eattofit.sports.application.membersports;

import com.flab.eattofit.sports.application.membersports.dto.MemberSportsesRequest;
import com.flab.eattofit.sports.application.sports.SportsService;
import com.flab.eattofit.sports.application.sports.event.events.MemberSportsesSubmittedEvent;
import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import com.flab.eattofit.sports.domain.membersports.MemberSportsesRepository;
import com.flab.eattofit.exercise.exception.sports.sports.exceptions.SportsNotFoundException;
import com.flab.eattofit.sports.infrastructure.membersports.MemberSportsesFakeRepository;
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
import static com.flab.eattofit.sports.fixture.membersports.MemberSportsesRequestFixture.회원_선호_스포츠_등록_두개_요청;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberSportsesServiceTest {

    private MemberSportsesService memberSportsesService;
    private MemberSportsesRepository memberSportsesRepository;

    @Mock
    private SportsService sportsService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        memberSportsesRepository = new MemberSportsesFakeRepository();
        memberSportsesService = new MemberSportsesService(memberSportsesRepository);
        Events.setPublisher(eventPublisher);
    }

    @Nested
    class 회원_선호_스포츠_등록 {

        @Test
        void 회원_선호_스포츠를_등록한다() {
            // given
            MemberSportsesRequest request = 회원_선호_스포츠_등록_두개_요청();
            Long memberId = 1L;

            doNothing().when(sportsService).validateSportsIds(anySet());

            // when
            memberSportsesService.submitMemberSportes(request, memberId);
            Optional<MemberSportses> memberSportses = memberSportsesRepository.findByMemberId(memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThatCode(() -> verify(eventPublisher, times(1))
                        .publishEvent(any(MemberSportsesSubmittedEvent.class))).doesNotThrowAnyException();
                softly.assertThat(memberSportses).isPresent();
                softly.assertThat(memberSportses.get().getSports()).hasSize(2);
            });
        }

        @Test
        void 없는_스포츠를_등록하려_하면_예외가_발생한다() {
            // given
            MemberSportsesRequest request = 회원_선호_스포츠_등록_두개_요청();
            Long memberId = 1L;

            doThrow(new SportsNotFoundException()).when(eventPublisher).publishEvent(any(MemberSportsesSubmittedEvent.class));

            // TODO: assertThatCode(...).publishEvent(...).hasCauseInstanceOf(SportsNotFoundException.class) 검증 구현 방법 찾기
            // when & then
            assertSoftly(softly -> {
                softly.assertThatThrownBy(() -> memberSportsesService.submitMemberSportes(request, memberId))
                        .isInstanceOf(SportsNotFoundException.class);
                /* softly.assertThatCode(() -> verify(eventPublisher, times(1))
                        .when(memberSportsesService.submitMemberSportses(request, memberId))
                        .publishEvent(any(MemberSportsesSubmittedEvent.class))).hasCauseInstanceOf(SportsNotFoundException.class); */
            });
        }
    }
}
