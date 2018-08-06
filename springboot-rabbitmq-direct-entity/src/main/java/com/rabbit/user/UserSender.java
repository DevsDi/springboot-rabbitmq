package com.rabbit.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        User user=new User();
        user.setName("戴文");
        user.setPass("123123");
        this.rabbitTemplate.convertAndSend("userQueue", user);
    }

}