package com.zyao.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 13:20
 * @Description Redis配置类
 *
 * 使用 @Configuration和 @Bean 注解   使用的是cglib动态代理 创建template实例（单例），并注入进spring容器
 * 使用 @Component @Bean 注解创建template实例（每一次调用都会创建新的template实例例），并注入进spring容器
 * Component
 */
//@Component
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
