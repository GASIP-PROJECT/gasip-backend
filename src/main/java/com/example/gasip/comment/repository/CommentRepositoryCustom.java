package com.example.gasip.comment.repository;

import com.example.gasip.comment.model.Comment;

public interface CommentRepositoryCustom {
    void addCommentLikeCount(Comment comment);

    void subCommentLikeCount(Comment comment);

    void addReportCount(Comment comment);
    void subReportCount(Comment comment);
}
