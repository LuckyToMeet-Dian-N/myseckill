package com.wen.seckill.aspect;


import com.wen.seckill.constant.Constants;
import com.wen.seckill.exception.UnloginException;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.utils.RequestAndResponseUtils;
import com.wen.seckill.vo.Users;
import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import sun.applet.Main;


import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 拦截未登录的用户
 * @Author: Gentle
 * @date 2018/8/18 23:53
 */
@Order(5)
@Slf4j
@Component
@Aspect
public class LoginAuthorization {
    //原子类计算访问量
      AtomicInteger atomicInteger= new AtomicInteger();
    @Autowired
    RedisService redisService;

    @Pointcut("execution(public * com.wen.seckill.controller.SeckillController.doSeckill(..)))")
    public void authorization() {
    }

    @Before("authorization()")
    public void handlerControllerMethod() throws Exception {
        /**
         * 如下是正常流程
         */
//        HttpServletRequest httpServletRequest = RequestAndResponseUtils.getRequest();
////
//        String token = httpServletRequest.getHeader("token");
//        //判断令牌在不在
//        if (StringUtils.isEmpty(token)) {
//            throw new UnloginException("还没有登录");
//        }
//        //判断令牌有没过期
//        String userJsonString = redisService.hashGet(Constants.USER_SESSION + ":" + token, Constants.USER_SESSION_FIELD);
//        if (userJsonString == null) {
//            throw new UnloginException("长时间未操作，请重新登录");
//        }
//
//        // 从redis中取用户信息
//        Users users = JsonUtils.jsonToObject(userJsonString, Users.class);
//        //请求转发到Controller
//        httpServletRequest.setAttribute(Constants.USER_SESSION, users);

        /**
         * 如下代码测试作用，提前先把用户信息放入redis。然后随机取出来当一个用户模拟访问，并发测试可以用jmeter
         */
        log.info("第"+atomicInteger.incrementAndGet()+"个访问请求");
        HttpServletRequest httpServletRequest = RequestAndResponseUtils.getRequest();
        //模拟秒杀，随机用户
        int a = (int)(Math.random()*500);
        //从redis中取用户信息
        Users  users=JsonUtils.jsonToObject(redisService.get(Constants.USER_SESSION+":"+a),Users.class);
        //请求转发到Controller
        httpServletRequest.setAttribute(Constants.USER_SESSION,users );
    }

}
