package com.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRabbitmqFanoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqFanoutApplication.class, args);
	}
	
	@Bean
	public Queue queueA() {
		return new Queue("fanout.A");
	}
	@Bean
	public Queue queueB() {
		return new Queue("fanout.B");
	}
	@Bean
	public Queue queueC() {
		return new Queue("fanout.C");
	}
	
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanout.exchange");
    }
    
    @Bean
    Binding bindQ1(Queue queueA, FanoutExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange);
    }
    
    @Bean
    Binding bindQ2(Queue queueB, FanoutExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange);
    }
    
    @Bean
    Binding bindQ3(Queue queueC, FanoutExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange);
    }
}
