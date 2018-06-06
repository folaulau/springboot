package com.folaukaveinga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CachingConfig{

//	@Bean
//	public JedisConnectionFactory redisConnectionFactory() {
//		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		jcf.setHostName("127.0.0.1");
//		jcf.setPort(6379);
//		jcf.setUsePool(true);
//		return jcf;
//	}
//	
//	@Bean("redisTemplate")
//	public RedisTemplate<String, Object> redisTemplate() {
//	  RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//	  redisTemplate.setConnectionFactory(redisConnectionFactory());
//	  //redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//	  return redisTemplate;
//	}
//	
//	@Bean
//	public RedisSerializer<String> redisSerializer() {
//		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//		return stringRedisSerializer;
//	} 
	 
	
//	@Bean
//	public CacheManager cacheManager() {
//	  RedisCacheManager cacheManager = RedisCacheManager.create(redisConnectionFactory());
//	  cacheManager.afterPropertiesSet();
//	  return cacheManager;
//	}

}
