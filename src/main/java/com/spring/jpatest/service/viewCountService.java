package com.spring.jpatest.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class viewCountService {
    
    private final RedisTemplate<String, Integer> redisTemplate;

    public viewCountService(RedisTemplate<String, Integer> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    // 특정 게시글이 조회되었는지 확인(존재하면 true, 존재하지 않으면 false)
    public boolean isViewed(int boardCd) {
        String key = "boardCd" + boardCd;
        return redisTemplate.opsForSet().isMember(key,boardCd);
    }

    public void addView(int boardCd) {
        String key = "boardCd" + boardCd;
        redisTemplate.opsForSet().add(key, boardCd);
        // key value 방식 고민
    }

    // isViewed를 호출하여 이미 해당 페이지 조회 여부 확인후 Redis에 게시글 등록
    // 조회하지 않은 게시글이면 조회수를 1증가 시키고 set에 key value를 추가한다
    public void increaseViewCount(int boardCd, int viewCnt) {
        String key = "viewCnt" + boardCd;
        addView(boardCd);
        redisTemplate.opsForSet().add(key, viewCnt + 1);
    }

    // 해당 게시글의 조회수 조회
    public int getViewCount(int boardCd) {
        String key = "viewCnt" + boardCd;
        return redisTemplate.opsForValue().get(key);
    }
}
