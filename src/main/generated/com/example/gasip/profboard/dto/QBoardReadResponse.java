package com.example.gasip.profboard.dto;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.gasip.board.dto.QBoardReadResponse is a Querydsl Projection type for BoardReadResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardReadResponse extends ConstructorExpression<BoardReadResponse> {

    private static final long serialVersionUID = 973240231L;

    public QBoardReadResponse(com.querydsl.core.types.Expression<java.time.LocalDateTime> regDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> updateDate, com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Long> clickCount, com.querydsl.core.types.Expression<Long> likeCount, com.querydsl.core.types.Expression<Long> profId) {
        super(BoardReadResponse.class, new Class<?>[]{java.time.LocalDateTime.class, java.time.LocalDateTime.class, long.class, String.class, long.class, long.class, long.class}, regDate, updateDate, postId, content, clickCount, likeCount, profId);
    }

}

