package com.wen.seckill;

import com.alibaba.fastjson.JSON;
import com.wen.seckill.constant.Constants;
import com.wen.seckill.domian.SeckillOrder;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.vo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {
	@Autowired
	RedisService redisService;
	@Test
	public void contextLoads() {


		Users users= new Users();
		users.setUser_name("Gentle");
		users.setBirthday(new Date());
		users.setPassword("123456");
		users.setUser_account("Gentle");
		users.setUser_id(1);

		String  json =JsonUtils.ObjectTojson(users);
		System.out.println("未用redis的set操作前的json值："+json);
		redisService.set("test",json);
		String errorJson= redisService.get("test");
		System.out.println("从redis中取出的刚刚放入的json值："+errorJson);
		//使用fastjson解析成对象,这里会报错
		Users users1=JSON.parseObject(errorJson,Users.class);


	}

}
