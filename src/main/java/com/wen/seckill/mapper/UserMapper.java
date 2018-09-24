package com.wen.seckill.mapper;

import com.wen.seckill.vo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 简单用户操作都在这写
 * @Author: Gentle
 * @date 2018/9/20  19:35
 */
@Mapper
public interface UserMapper {
    @Select("select * from users where user_account=#{account} ")
    Users selectUser(@Param("account") String account );



}
