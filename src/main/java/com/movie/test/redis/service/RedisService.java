package com.movie.test.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void setData(String key, String value){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setData(String key, String value, long duration){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public String getData(String key){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        return valueOperations.get(key) == null ? null : valueOperations.get(key);
    }

    public void setDataList(String key, String data){
        redisTemplate.opsForList().rightPushAll(key, data);
    }

    public List<String> getDataList(String key){
        Long len = redisTemplate.opsForList().size(key);
        return len==0? new ArrayList<>() : redisTemplate.opsForList().range(key, 0, len-1);
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }

    public long calculateTimeUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.truncatedTo(ChronoUnit.DAYS).plusDays(1);
        return ChronoUnit.SECONDS.between(now, midnight);
    }
}
