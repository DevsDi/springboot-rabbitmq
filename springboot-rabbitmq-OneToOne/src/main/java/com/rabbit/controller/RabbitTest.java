package com.rabbit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.hello.Sender;

@RestController
public class RabbitTest {
    
    @Autowired
    private Sender sender;
    
    @GetMapping("/OneToOne")
    public void test() {
        sender.send();
    }
}