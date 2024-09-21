package com.example.gasip.memberBlock.dto;

import com.example.gasip.memberBlock.model.MemberBlock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MemberBlockResponse {
    private Long blockId;
    private Long blocker;
    private Long blocked;

    public static MemberBlockResponse formEntity(MemberBlock memberBlock) {
        return MemberBlockResponse.builder()
                .blockId(memberBlock.getBlockId())
                .blocker(memberBlock.getBlocker().getMemberId())
                .blocked(memberBlock.getBlocked().getMemberId())
                .build();
    }
}
