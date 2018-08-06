package com.dev.service;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dev.config.RabbitConfig;
import com.rabbitmq.client.Channel;

@Component
public class TtlHandler {

	 private Logger log = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = {RabbitConfig.my_queue_name})
    public void listenerDelayQueue(String msg, Message message, Channel channel) {
        log.info("msg == "+msg +"[接收时间] - "+LocalDateTime.now());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }
}