package com.example.gasip.commentLikes.service;


import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.commentLikes.dto.CommentLikesRequestDto;
import com.example.gasip.commentLikes.model.CommentLikes;
import com.example.gasip.commentLikes.repository.CommentLikesRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.DuplicateResourceException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class CommentLikesService {
    private final CommentLikesRepository commentLikesRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void insertComment(CommentLikesRequestDto commentLikesRequestDto, MemberDetails memberDetails) throws Exception {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentLikesRequestDto.getPostId());

        Comment comment = commentRepository.findById(commentLikesRequestDto.getCommentId())
                .orElseThrow(() -> new NotFoundException(("Could not found comment id : " + commentLikesRequestDto.getCommentId())));

        if (commentLikesRepository.findByMemberAndCommentAndBoard(member, comment, board).isPresent()) {
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_LIKE);
        }

        CommentLikes commentLikes = CommentLikes.builder()
                .comment(comment)
                .member(member)
                .board(board)
                .build();

        commentLikesRepository.save(commentLikes);
        commentRepository.addCommentLikeCount(comment);
    }

    @Transactional
    public void deleteComment(CommentLikesRequestDto commentLikesRequestDto, MemberDetails memberDetails) {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentLikesRequestDto.getPostId());

        Comment comment = commentRepository.findById(commentLikesRequestDto.getCommentId())
                .orElseThrow(() -> new NotFoundException(("Could not found comment id : " + commentLikesRequestDto.getCommentId())));

        CommentLikes commentLikes = commentLikesRepository.findByMemberAndCommentAndBoard(member, comment, board)
                .orElseThrow(() -> new NotFoundException("could not found comment like id"));


        commentLikesRepository.delete(commentLikes);
        commentRepository.subCommentLikeCount(comment);
    }

    @Transactional
    public Boolean isCommentLikes(Long commentId, Long memberId) {
        return commentLikesRepository.existsByComment_CommentIdAndMember_MemberId(commentId, memberId);
    }
}
