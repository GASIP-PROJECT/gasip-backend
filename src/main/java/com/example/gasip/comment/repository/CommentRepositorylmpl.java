//package com.example.gasip.comment.repository;
//
//import com.example.gasip.comment.model.Comment;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class CommentRepositorylmpl {
//    private final JpaQueryMethodFactory jpaQueryMethodFactory;
//
//    public List<Comment> findCommentByTicketId(Long ticketId) {
//        return jpa.selectFrom(comment)
//                .leftJoin(comment.parent)
//                .fetchJoin()
//                .where(comment.ticket.id.eq(ticketId))
//                .orderBy(
//                        comment.parent.id.asc().nullsFirst(),
//                        comment.createdAt.asc()
//                ).fetch();
//    }
//
//}
