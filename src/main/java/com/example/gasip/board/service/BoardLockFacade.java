package com.example.gasip.board.service;

import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BoardLockFacade {
    private final BoardService boardService;
    private final RedissonClient redissonClient;

    public BoardReadResponse insertView(Long postId) {
        RLock lock = redissonClient.getLock(String.format("click:%d", postId));
        BoardReadResponse boardReadResponse;
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            boardReadResponse = boardService.addViewWithoutMember(postId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
        return boardReadResponse;
    }
}
