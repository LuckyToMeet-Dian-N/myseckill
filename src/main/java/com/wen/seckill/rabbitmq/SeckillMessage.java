package com.wen.seckill.rabbitmq;

import com.wen.seckill.vo.Users;
import lombok.Data;
/**
 * @Description: 用户传递消息的对象
 * @Author: Gentle
 * @date 2018/9/19  16:01
 */
@Data
public class SeckillMessage {
    private Long product_id;
    private Users users;
}
