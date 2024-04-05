package com.example.gasip.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisMailService {
    private final RedisTemplate redisTemplate;

    public void setValues(String key, String authCode, Duration duration) {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,authCode,duration);
    }

    public String getValues(String key) {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public boolean checkExistsValue(String redisAuthCode) {
        return !redisAuthCode.equals("false");
    }
}
