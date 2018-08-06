package com.rabbit.fanout;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String msgString="fanoutSender========";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanout.exchange","abcd.ee", msgString);
    }

}