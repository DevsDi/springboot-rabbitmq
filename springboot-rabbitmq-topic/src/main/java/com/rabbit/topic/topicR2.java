package com.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue2")
public class topicR2 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicR2 : " +msg);
    }

}