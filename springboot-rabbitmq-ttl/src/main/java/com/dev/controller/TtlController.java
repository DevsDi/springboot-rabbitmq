package com.dev.controller;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.config.RabbitConfig;

@RestController
public class TtlController {

	 private Logger log = LoggerFactory.getLogger(getClass());

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TtlController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/dlx")
    public void defaultMessage() {
    	String msg="hello dlx";
        // 添加延时队列
        this.rabbitTemplate.convertAndSend(RabbitConfig.register_delay_exchange, RabbitConfig.delay_routing_key, msg, message -> {
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        log.info("[发送时间] - "+LocalDateTime.now());
    }

}