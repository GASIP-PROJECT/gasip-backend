package com.example.gasip.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisViewCountService {
    private final StringRedisTemplate stringRedisTemplate;

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setDateExpire(String key, String value, long duration) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void addViewCountInRedis(Long boardId) {
        String key = String.valueOf(boardId);
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        if (getData(key) == null) {
            valueOperations.set(key,"0");
            setOperations.add("keyList", key);
        }
        valueOperations.increment(key);

        if (!setOperations.isMember("keyList",getData(key))) {
            setOperations.add("keyList",key);
        }
    }

    public List<String> deleteViewCountInRedis() {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        List<String> value = setOperations.pop("keyList",setOperations.size("keyList"));
        System.out.println(value);
        return value;
    }

    public void deleteData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.getAndDelete(key);
    }
}
