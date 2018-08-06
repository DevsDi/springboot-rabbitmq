package com.rabbit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.hello.HelloSender1;

@RestController
public class RabbitTest {
    
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    
    @GetMapping("/hello")
    public void hello() {
        helloSender1.send("hello1");
    }
    
    /**
     * 单生产者-多消费者
     * @throws InterruptedException 
     */
    @GetMapping("/oneToMany")
    public void oneToMany() throws InterruptedException {
        for(int i=0;i<10;i++){
        	Thread.sleep(200);
            helloSender1.send("hellomsg: "+i);
        }
        
    }
}