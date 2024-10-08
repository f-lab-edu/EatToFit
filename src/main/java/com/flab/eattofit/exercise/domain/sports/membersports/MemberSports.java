package com.flab.eattofit.exercise.domain.sports.membersports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberSports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sportsId;

    private MemberSports(final Long sportsId) {
        this.sportsId = sportsId;
    }

    public static MemberSports createWith(final Long sportsId) {
        return new MemberSports(sportsId);
    }
}
