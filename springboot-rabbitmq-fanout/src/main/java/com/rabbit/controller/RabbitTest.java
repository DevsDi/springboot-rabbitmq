package com.rabbit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.fanout.FanoutSender;

@RestController
public class RabbitTest {
    
    @Autowired
    private FanoutSender fanoutSender;

    @GetMapping("/fanoutTest")
    public void fanoutTest() {
           fanoutSender.send();
    }
}