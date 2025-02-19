package com.spring.jpatest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    // LettuceConnectionFactory를 활용하여 Redis연결 팩토리를 생성한다.
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Jedis와 Lettuce중에 Lettuce를 사용했는데 Jedis는 멀티 쓰레드가 불안정하고 Pool의 한계가 있고
        // Lettuce는 Netty 기반이라 비동기 지원 가능하여 Lettuce를 사용했다.
        // 클러스터를 지원하여 Redis서버와 통신가능
        return new LettuceConnectionFactory(host, port);
    }

    // RedisTemplate : Spring data Redis에서 제공하는 Redis를 제어하기위한 클래스
    @Bean
    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory); // 등록한 Redis 팩토리
        redisTemplate.setDefaultSerializer(new StringRedisSerializer()); 
        // StringRedisSerializer : 문자열을 바이트 코드로 변환하는 직렬화 도구
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
        // 조회수 및 좋아요의 Key값 타입이 String이기에 타입변환을 위해 추가
        
        return redisTemplate;
    }
}
