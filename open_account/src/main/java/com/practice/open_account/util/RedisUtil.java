package com.practice.open_account.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	@Bean
	public ValueOperations<Object, Object> getOperations(){
		return redisTemplate.opsForValue();
	}
}
