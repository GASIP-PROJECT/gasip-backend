package com.example.gasip.likes.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.DuplicateResourceException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.likes.dto.LikeRequestDto;
import com.example.gasip.likes.model.Likes;
import com.example.gasip.likes.repository.LikeRepository;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void insert(LikeRequestDto likeRequestDto, MemberDetails memberDetails) throws Exception {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));

        // 좋아요 중복 요청 시 에러 반환(이미 좋아요 되어있는 경우)
        if (likeRepository.findByMemberAndBoard(member, board).isPresent()){
//            throw new DuplicateResourceException("already exist data by member id :" + member.getMemberId() + " ,"
//                    + "board id : " + board.getPostId());
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_LIKE);
        }

        Likes likes = Likes.builder()
                .board(board)
                .member(member)
                .build();

        likeRepository.save(likes);
        boardRepository.addLikeCount(board);
    }


    @Transactional
    public void delete(LikeRequestDto likeRequestDto, MemberDetails memberDetails) {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));

        Likes likes = likeRepository.findByMemberAndBoard(member, board)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));


        likeRepository.delete(likes);
        boardRepository.subLikeCount(board);
    }

    /**
     * 좋아요 체크
     */
    @Transactional
    public Boolean isLikes(Long postId, Long memberId) {
        return likeRepository.existsByBoard_PostIdAndMember_MemberId(postId, memberId);
    }


    /**
     *
     * 부하테스트용
     *
     */
    @Transactional
    public void addLikeWithoutMember(LikeRequestDto likeRequestDto) throws Exception {

        Board board = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));


        Likes likes = Likes.builder()
                .board(board)
                .build();

        likeRepository.save(likes);
        boardRepository.addLikeCount(board);
    }
}
