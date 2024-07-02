package com.example.gasip.board.service;

import com.example.gasip.board.dto.BoardReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisBestBoardService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void addBestBoardList(List<BoardReadResponse> bestBoardReadResponseList) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("bestBoard", bestBoardReadResponseList);
    }

    public List<BoardReadResponse> getData(String key) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        return (List<BoardReadResponse>) valueOperations.get(key);
    }
}
