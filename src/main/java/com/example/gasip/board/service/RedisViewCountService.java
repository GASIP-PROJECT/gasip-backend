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
    public void addBoardId(String key, String value) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.append(key, value);
    }

    public void addViewCountInRedis(Long boardId) {
        String key = String.valueOf(boardId)+"board";
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        if (getData(key) == null) {
            valueOperations.set(key,"0");
            setOperations.add("keyList", key.replace("board",""));
        }
        if (!setOperations.isMember("keyList",getData(key))) {
            setOperations.add("keyList",key.replace("board",""));
        }
        valueOperations.increment(key);
    }

    public List<String> deleteViewCountInRedis() {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        List<String> value = setOperations.pop("keyList",setOperations.size("keyList"));
        return value;
    }

    public String getAndDeleteData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.getAndDelete(key+"board");
    }
}
