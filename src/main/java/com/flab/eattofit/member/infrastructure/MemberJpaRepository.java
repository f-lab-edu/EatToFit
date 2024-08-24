package com.flab.eattofit.member.infrastructure;

import com.flab.eattofit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member save(Member member);
    Optional<Member> findById(Long id);
    boolean existsByNickname(String nickname);
}
