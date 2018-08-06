package com.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class SpringbootRabbitmqTopicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqTopicApplication.class, args);
	}
    @Bean
    public Queue queue1() {
        return new Queue("queue1");
    }
    @Bean
    public Queue queue2() {
        return new Queue("queue2");
    }
    
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    
    @Bean
    Binding bindQ1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("topic.add");
    }
    
    @Bean
    Binding bindQ2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("topic.#");
    }
}
