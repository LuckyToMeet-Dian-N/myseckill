package com.wen.seckill.utils;


import com.alibaba.fastjson.JSON;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.vo.Users;

import java.util.List;

/**
 * @Description: json工具类 支持各种数据类型转换
 * @Author: Gentle
 * @date 2018/9/17  9:43
 */
public class JsonUtils {

    //默认日期格式（年月日时分秒）
    public static final String default_dateFormat = "yyyy-MM-dd HH:mm:ss";
    //存在时间格式（年月日）
    public static final String dateFormat = "yyyy-MM-dd";

    /**
     * json字符串转对象
     *
     * @param str   字符串
     * @param clazz 需要转成想要的对象
     * @param <T>   返回相应对象
     * @return
     */
    public static <T> T jsonToObject(String str, Class<T> clazz) {
        //这里有个极其无语的大坑。。自己注意了，主要针对redis的set操作
        String json =String.valueOf(JSON.parse(str));
//        String json =JSON.parse(str).toString();
        return JSON.parseObject(json, clazz);

    }

    /**
     * 对象转json字符串，默认不执行进行日期转换
     *
     * @param obj 对象
     * @return
     */
    public static String ObjectTojson(Object obj) {

        return ObjectTojson(obj, false);
    }

    /**
     * 对象转json字符串，使用默认日期转换
     *
     * @param obj           对象
     * @param useDateFormat 自定义时间格式
     * @return
     */
    public static String ObjectTojson(Object obj, boolean useDateFormat) {

        return ObjectTojson(obj, useDateFormat, default_dateFormat);
    }

    /**
     * 自定义日期格式
     *
     * @param obj
     * @param dateFormat
     * @return
     */
    public static String ObjectTojson(Object obj, String dateFormat) {

        return ObjectTojson(obj, true, dateFormat);

    }

    /**
     * 对象转字符串，总处理方法，不对外开放
     *
     * @param obj           javabean对象
     * @param useDateFormat
     * @param dateFormat
     * @return
     */
    private static String ObjectTojson(Object obj, boolean useDateFormat, String dateFormat) {
        if (useDateFormat) {
            return JSON.toJSONStringWithDateFormat(obj, dateFormat);
        }
        return JSON.toJSONString(obj);

    }

    /**
     * json格式解析为List集合，不解决格式时间问题
     *
     * @param str   json字符串
     * @param clazz 要转换的对象
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonTolist(String str, Class<T> clazz) {

        return JSON.parseArray(str, clazz);
    }


}
