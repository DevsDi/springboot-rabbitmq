package com.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rabbit.queue.QueueName;

@SpringBootApplication
public class SpringbootRabbitmqDemoApplication {
	
    @Bean
    public Queue helloQueue() {
        return new Queue(QueueName.OneToOneQueue);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqDemoApplication.class, args);
	}
}
