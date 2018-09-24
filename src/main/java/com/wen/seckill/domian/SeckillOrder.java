package com.wen.seckill.domian;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 秒杀订单
 * @Author: Gentle
 * @date 2018/9/17  22:49
 */
@Data
public class SeckillOrder {
    private Long order_id;
    private Long product_id;
    private Integer user_id;
    private Date order_time;
    private Integer status;
}
