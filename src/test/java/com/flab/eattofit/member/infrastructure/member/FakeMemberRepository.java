package com.flab.eattofit.member.infrastructure.member;

import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public Member save(final Member member) {
        Member savedMember = Member.builder()
                .id(id)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();

        map.put(id++, savedMember);
        return savedMember;
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return map.values()
                .stream()
                .filter(member -> id.equals(member.getId()))
                .findAny();
    }

    @Override
    public Optional<Member> findByEmail(final String email) {
        return map.values()
                .stream()
                .filter(member -> email.equals(member.getEmail()))
                .findAny();
    }

    @Override
    public boolean existsByNickname(final String nickname) {
        return map.values()
                .stream()
                .anyMatch(member -> nickname.equals(member.getNickname()));
    }
}
