package com.example.gasip.likes.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.likes.dto.LikeRequestDto;
import com.example.gasip.likes.model.Likes;
import com.example.gasip.likes.repository.LikeRepository;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.redisson.RedissonLock;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceInRedis {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @RedissonLock(value = "#postId")
    public void redissonAddLike(LikeRequestDto likeRequestDto) {
        Board board = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));


        Likes likes = Likes.builder()
                .board(board)
                .build();

        likeRepository.save(likes);
        boardRepository.addLikeCount(board);

    }
}
