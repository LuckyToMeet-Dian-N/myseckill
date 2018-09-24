package com.wen.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 下订单的对象，这里偷懒和数据库字段名一致（正常开发时尽量不要一致）
 * @Author: Gentle
 * @date 2018/9/19  15:47
 */
@Data
public class OrderInfo {
    private String order_id;
    private Long product_id;
    private Integer user_id;
//    private Date order_time;
    private Integer status;




}
