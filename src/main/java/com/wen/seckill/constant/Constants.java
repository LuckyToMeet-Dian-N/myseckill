package com.wen.seckill.constant;

/**
 * @Description: 常量类
 * @Author: Gentle
 * @date 2018/9/17  14:11
 */
public final class Constants {
    //全部秒杀商品
    public static final String ALL_SECKILL_PRODECT = "ALL_SECKILL_PRODECT";
    //秒杀商品
    public static final String SECKILL_PRODECT = "SECKILL_PRODECT";

    //秒杀到的用户商品
    public static final String USER_PRODUCT_BY_SECKILL = "USER_PRODUCT_BY_SECKILL";


    //默认redis过期时间(半小时)
    public static final int DEAUFALT_REDIS_EXPIRE = 1800;

    //用户令牌
    public static final String USER_SESSION = "USER_SESSION";
    //令牌过期时间
    public static final String USER_SESSION_EXPIRE = "USER_SESSION_EXPIRE";
    //用户令牌在哈希field
    public static final String USER_SESSION_FIELD = "USER_SESSION_FIELD";

}
