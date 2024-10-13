package com.flab.eattofit.fitness.domain.memberfitness;

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
public class MemberFitness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long fitnessId;

    private MemberFitness(final Long fitnessId) {
        this.fitnessId = fitnessId;
    }

    public static MemberFitness createWith(final Long fitnessId) {
        return new MemberFitness(fitnessId);
    }
}
