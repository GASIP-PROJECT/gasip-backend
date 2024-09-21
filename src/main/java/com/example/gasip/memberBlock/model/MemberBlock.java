package com.example.gasip.memberBlock.model;

import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "MemberBlock")
public class MemberBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    @Schema(description = "ID", example = "1")
    private Long blockId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocker_id")
    @Schema(description = "blocker 유저 ID입니다.(현재 로그인된 유저)", example = "4")
    private Member blocker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_id")
    @Schema(description = "blocked 유저 ID입니다.", example = "412")
    private Member blocked;

    public MemberBlock(Member blocker, Member blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
    }
}
