package com.wen.seckill.service.Impl;

import com.wen.seckill.constant.Constants;
import com.wen.seckill.exception.CheckException;
import com.wen.seckill.mapper.UserMapper;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.service.LoginService;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.vo.Users;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 登录服务。密码未做加密，可自行更改
 * @Author: Gentle
 * @date 2018/9/20 0020 19:29
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    public String selectUser(String account, String password){

        //判断传入参数是否为空或“”
        if (StringUtils.isEmpty(account)|| StringUtils.isEmpty(password)){
            throw new CheckException("用户名或密码为空");
        }
        //这里是去前后空格
        account = account.trim();
        password= password.trim();
        //根据账号查询用户信息
        Users users = userMapper.selectUser(account);
        if(users==null){
            throw  new CheckException("用户名或密码错误");
        }
        //判断密码是否一致
        if (!users.getPassword().equals(password)){
            throw  new CheckException("用户名或密码错误");
        }
        //这里应该写一个自定义算法，来生成专门的token放入redis中，这里偷懒，就不自己写了。随便来个uuid做简单应付
        String token =UUID.randomUUID().toString();

        redisService.hashSet(Constants.USER_SESSION+":"+token,Constants.USER_SESSION_FIELD,users,Constants.DEAUFALT_REDIS_EXPIRE);

        return token;
    }


    public static void main(String[] args) {
        Users users = new Users();
        users.setPassword("123");
        users.setBirthday(new Date());
        users.setUser_name("Gentle");
        String json  =JsonUtils.ObjectTojson(users);



    }









}
