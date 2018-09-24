package com.wen.seckill.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
	private String host;
	private int port;
	private int timeout;
	private int poolMaxTotal;
	private int poolMaxWait;
	private int poolMaxIdle;


}
