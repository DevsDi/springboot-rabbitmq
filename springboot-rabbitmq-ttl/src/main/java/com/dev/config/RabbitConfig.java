package com.dev.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    /**
     * 延迟队列 TTL 名称
     */
    private static final String register_delay_queue = "delay.queue";
    /**
     * DLX，dead letter发送到的 exchange
     *  此处的 exchange 很重要,具体消息就是发送到该交换机的
     */
    public static final String register_delay_exchange = "delay.exchange";
    /**
     * routing key 名称
     *  此处的 routingKey 很重要要,具体消息发送在该 routingKey 的
     */
    public static final String delay_routing_key = "";


    public static final String my_queue_name = "myqueue";
    public static final String my_exchange_name = "myexchange";
    public static final String my_routing_key = "all";

    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     *  第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     *  第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", my_exchange_name);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", my_routing_key);
        
        return new Queue(register_delay_queue, true, false, false, params);
    }

    /**
     * @return DirectExchange
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(register_delay_exchange);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(delay_routing_key);
    }


    @Bean
    public Queue registerQueue() {
        return new Queue(my_queue_name, true);
    }

    @Bean
    public TopicExchange registerTopicExchange() {
        return new TopicExchange(my_exchange_name);
    }

    @Bean
    public Binding registerkBinding() {
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with(my_routing_key);
    }
}