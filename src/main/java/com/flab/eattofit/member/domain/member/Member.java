package com.flab.eattofit.member.domain.member;

import com.flab.eattofit.global.domain.SoftDeleteBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Member extends SoftDeleteBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private Member(final String email, final String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static Member of(final String email, final String nickname) {
        return new Member(email, nickname);
    }

    public void updateNicknameWithGenerator(final NicknameGenerator generator) {
        this.nickname = generator.generateNickname(nickname);
    }
}
