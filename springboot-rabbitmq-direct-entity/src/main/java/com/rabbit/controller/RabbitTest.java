package com.rabbit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.user.UserSender;

@RestController
public class RabbitTest {
    
    @Autowired
    private UserSender userSender;

    @GetMapping("/userTest")
    public void userTest() {
    	userSender.send();
    }
}