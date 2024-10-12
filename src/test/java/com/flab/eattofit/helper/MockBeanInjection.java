package com.flab.eattofit.helper;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.flab.eattofit.exercise.application.fitness.fitness.FitnessService;
import com.flab.eattofit.exercise.application.fitness.memberfitness.MemberFitnessesService;
import com.flab.eattofit.exercise.application.sports.membersports.MemberSportsesService;
import com.flab.eattofit.exercise.application.sports.sports.SportsService;
import com.flab.eattofit.exercise.domain.fitness.fitness.FitnessRepository;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnessesRepository;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportsesRepository;
import com.flab.eattofit.exercise.domain.sports.sports.SportsRepository;
import com.flab.eattofit.food.application.FoodService;
import com.flab.eattofit.member.application.auth.AuthService;
import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;
import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.domain.member.MemberRepository;
import com.flab.eattofit.member.domain.member.NicknameGenerator;
import com.flab.eattofit.member.ui.auth.interceptor.MemberLoginValidCheckerInterceptor;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationExtractor;
import com.flab.eattofit.member.ui.auth.support.OAuthProperties;
import com.flab.eattofit.member.ui.auth.support.resolver.AuthMemberArgumentResolver;
import com.flab.eattofit.member.ui.auth.support.resolver.OAuthArgumentResolver;
import com.flab.eattofit.profile.application.exerciseprofile.ExerciseProfileService;
import com.flab.eattofit.profile.application.physicalprofile.PhysicalProfileService;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfileRepository;
import com.flab.eattofit.profile.domain.physicalprofile.YearManager;
import com.flab.eattofit.storage.application.StorageManager;
import com.flab.eattofit.storage.application.StorageService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@MockBean(JpaMetamodelMappingContext.class)
public class MockBeanInjection {

    // auth
    @MockBean
    protected OAuthArgumentResolver oAuthArgumentResolver;

    @MockBean
    protected AuthMemberArgumentResolver authMemberArgumentResolver;

    @MockBean
    protected MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor;

    @MockBean
    protected MemberAuthenticationContext memberAuthenticationContext;

    @MockBean
    protected MemberAuthenticationExtractor authenticationExtractor;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected OAuthProperties oAuthProperties;

    @MockBean
    protected JsonMapper jsonMapper;

    @MockBean
    protected TokenManager tokenManager;

    @MockBean
    protected OAuthManager oAuthManager;

    @MockBean
    protected NicknameGenerator nicknameGenerator;

    @MockBean
    protected MemberRepository memberRepository;

    @MockBean
    protected RefreshTokenRepository refreshTokenRepository;

    // storage
    @MockBean
    protected StorageService storageService;

    @MockBean
    protected StorageManager storageManager;

    @MockBean
    protected AmazonS3 amazonS3;

    // fitness
    @MockBean
    protected FitnessService fitnessService;

    @MockBean
    protected FitnessRepository fitnessRepository;

    @MockBean
    protected MemberFitnessesService memberFitnessesService;

    @MockBean
    protected MemberFitnessesRepository memberFitnessesRepository;

    // sports
    @MockBean
    protected SportsService sportsService;

    @MockBean
    protected SportsRepository sportsRepository;

    @MockBean
    protected MemberSportsesService memberSportsesService;

    @MockBean
    protected MemberSportsesRepository memberSportsesRepository;

    // food
    @MockBean
    protected FoodService foodService;

    // profile
    @MockBean
    protected PhysicalProfileService physicalProfileService;

    @MockBean
    protected YearManager yearManager;

    @MockBean
    protected PhysicalProfileRepository physicalProfileRepository;

    @MockBean
    protected ExerciseProfileService exerciseProfileService;

    @MockBean
    protected ExerciseProfileRepository exerciseProfileRepository;
}
