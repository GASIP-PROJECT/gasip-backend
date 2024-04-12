package com.example.gasip.member.model;

import com.example.gasip.global.constant.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long memberId;

    @Column(nullable = false)
    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "이름", example = "정혜민")
    private String name;

    @Column(nullable = false)
    @Schema(description = "닉네임", example = "가십이")
    private String nickname;

    @Column(nullable = false)
    @Schema(description = "비밀번호", example = "1234!@")
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "권한", example = "MEMBER")
    private Role role;

    @Builder
    private Member(String email, String name, String nickname, String password, Role role) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public static Member create(String email, String name, String nickname, String password, Role role) {
        return new Member(email, name, nickname,password, role);
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public void updateNickname(String name) {
        this.nickname = name;
    }
}
