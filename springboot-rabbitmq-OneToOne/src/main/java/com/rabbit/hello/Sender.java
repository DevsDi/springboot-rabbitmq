package com.rabbit.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbit.queue.QueueName;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void send() {
        String sendMsg = "Sender: hello rabbitMQ ";
        System.out.println(sendMsg);
        //向指定Queue发送文本消息
        this.rabbitTemplate.convertAndSend(QueueName.OneToOneQueue, sendMsg);
    }

}