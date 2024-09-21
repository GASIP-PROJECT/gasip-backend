package com.example.gasip.memberBlock.dto;

import com.example.gasip.memberBlock.model.MemberBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class MemberBlockRequest {
    private Long blocked;

    public static MemberBlockRequest toEntity(MemberBlock memberBlock) {
        return MemberBlockRequest.builder()
                .blocked(memberBlock.getBlocked().getMemberId())
                .build();
    }
}
