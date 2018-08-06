package com.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class topicR1 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicR1 : " +msg);
    }

}