package com.spring.jpatest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {
    
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Jedis와 Lettuce중에 Lettuce를 사용했는데 Jedis는 멀티 쓰레드가 불안정하고 Pool의 한계가 있고
        // Lettuce는 Netty 기반이라 비동기 지원 가능하여 Lettuce를 사용했다.
        return new LettuceConnectionFactory(host, port);
    }
}
