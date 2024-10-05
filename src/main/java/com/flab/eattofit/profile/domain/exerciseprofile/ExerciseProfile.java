package com.flab.eattofit.profile.domain.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.vo.Experience;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Frequency;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Goal;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Level;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class ExerciseProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(nullable = false)
    private Experience experience;

    @Enumerated
    @Column(nullable = false)
    private Frequency frequency;

    @Enumerated
    @Column(nullable = false)
    private Goal goal;

    @Enumerated
    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private Long memberId;

    public static ExerciseProfile createWith(
            final String experience,
            final String frequency,
            final String goal,
            final String level,
            final Long memberId
    ) {
        return ExerciseProfile.builder()
                .experience(Experience.findByName(experience))
                .frequency(Frequency.findByName(frequency))
                .goal(Goal.findByName(goal))
                .level(Level.findByName(level))
                .memberId(memberId)
                .build();
    }
}
