package com.wen.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * @Description: rabbitmq的发送者，告诉他要干活了
 * @Author: Gentle
 * @date 2018/9/19  16:01
 */
@Component
@Slf4j
public class MQSender {
	@Autowired
	AmqpTemplate amqpTemplate ;
	
	public void sendMiaoshaMessage(String message) {
		log.info("rabbit发送消息");
		amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,message);

	}
}
