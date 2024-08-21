package com.flab.eattofit.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    boolean existsByNickname(String nickname);
}
