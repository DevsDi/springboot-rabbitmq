package com.rabbit.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbit.queue.QueueName;

@Component
@RabbitListener(queues = {QueueName.OneToOneQueue})//监控指定的Queue
public class Receiver {

    @RabbitHandler
    public void process(String revmsg) {
        System.out.println("Receiver  : " + revmsg);
    }

}