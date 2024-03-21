package com.example.gasip.board.service;

import com.example.gasip.board.dto.BoardReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardLockFacade {
    private final BoardService boardService;
    private final RedissonClient redissonClient;

    public BoardReadResponse insertView(Long postId) {
        RLock lock = redissonClient.getLock(String.format("click:%d", postId));
        BoardReadResponse boardReadResponse;
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                log.warn("redisson getLock timeout");
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