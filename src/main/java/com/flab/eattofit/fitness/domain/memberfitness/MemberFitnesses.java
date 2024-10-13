package com.flab.eattofit.fitness.domain.memberfitness;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class MemberFitnesses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @JoinColumn(name = "member_fitnesses_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<MemberFitness> fitnesses = new ArrayList<>();

    public static MemberFitnesses createWithMemberId(final Long memberId) {
        return MemberFitnesses.builder()
                .memberId(memberId)
                .fitnesses(new ArrayList<>())
                .build();
    }

    public void addFitness(final MemberFitness memberFitness) {
        this.fitnesses.add(memberFitness);
    }
}
