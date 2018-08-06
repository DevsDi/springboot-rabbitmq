package com.rabbit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.topic.TopicSender;

@RestController
public class RabbitTest {
    
    @Autowired
    private TopicSender topicSender;

    @GetMapping("/topicTest1")
    public void topicTest1() {
           topicSender.send1();
    }
    
    @GetMapping("/topicTest2")
    public void topicTest2() {
           topicSender.send2();
    }
}