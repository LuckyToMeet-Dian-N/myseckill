package com.wen.seckill.service;

import com.wen.seckill.domian.SeckillOrder;
import com.wen.seckill.vo.OrderInfo;
import com.wen.seckill.vo.Product;
import com.wen.seckill.vo.Users;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/17  13:54
 */
public interface OrdersService {
    /**
     * 判断是否重复秒杀
     * @return
     */
    SeckillOrder isRepeatSeckill(int userId,long productId);

    boolean reduceProductNumber(long productId,int version);

    int insertOrder(SeckillOrder seckillOrder );

    SeckillOrder doSeckill(Product product, Users users);


}
