package com.wen.seckill.service;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.classfile.Constant;

/**
 * @Description: 秒杀服务接口
 * @Author: Gentle
 * @date 2018/9/17  9:38
 */
public interface SeckillService {

    String doSeckill(long productId );

    /**
     * 初始化商品信息。用作秒杀
     * @return
     */
    String reSetProduct();

}
