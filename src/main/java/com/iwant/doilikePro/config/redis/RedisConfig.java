package com.iwant.doilikePro.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Configuration
public class RedisConfig {
	
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		
		template.setConnectionFactory(factory);
		
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		
		ObjectMapper om = new ObjectMapper();
		
		//JsonAutoDetect 很特别 手打 弄不出来 复制 import com.fasterxml.jackson.annotation.JsonAutoDetect; 然后就可以了
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		StringRedisSerializer redisSerializer = new StringRedisSerializer();
		
		//key 采用String 序列化方式
		template.setKeySerializer(redisSerializer);
		
		//hash 的 key 也采用String 序列化方式
		template.setHashKeySerializer(redisSerializer);
		
		//value 序列化方式 采用 jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		
		//hash 的 value 序列化方式 采用 jackson 
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		
		template.afterPropertiesSet();
		
		return template;
		
		
		
	}

}
