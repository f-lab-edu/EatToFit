package com.flab.eattofit.member.domain.member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    boolean existsByNickname(String nickname);
}
