package com.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send1() {
        String msg1 = "I am topic.mesaage msg==111111====";
        System.out.println("sender1 : " + msg1 +" routingKey==topic.add");
        this.rabbitTemplate.convertAndSend("exchange", "topic.add", msg1);
    }
    public void send2() {
        String msg1 = "I am topic.mesaage msg===222222===";
        System.out.println("sender2 : " + msg1+" routingKey==topic.delete");
        this.rabbitTemplate.convertAndSend("exchange", "topic.delete", msg1);
    }

}