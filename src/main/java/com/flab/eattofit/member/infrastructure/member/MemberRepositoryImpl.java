package com.flab.eattofit.member.infrastructure.member;

import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(final Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public boolean existsByNickname(final String nickname) {
        return memberJpaRepository.existsByNickname(nickname);
    }
}
