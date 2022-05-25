package com.huang.delayedQueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Time 2021-12-28 9:26
 * Created by Huang
 * className: DelayedRabbitConfig
 * Description:
 */
@Configuration
public class DelayedRabbitConfig {

    /***
     * @Description: 死信队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue delayedQueue(){
        return new Queue("delay_queue",true,false,false);
    }

    /***
     * @Description: 普通消费队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue commonQueue(){
        Map<String,Object> map = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        map.put("x-dead-letter-exchange", "dlx_exchange");
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        map.put("x-dead-letter-routing-key", "delayed");
        // x-message-ttl 声明消息存活时间
        map.put("x-message-ttl", 10*1000);
        return new Queue("common_queue",true,false,false,map);
    }

    /***
     * @Description: dlx_exchange
     * @return: org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public DirectExchange delayedDirectExchange(){
        return new DirectExchange("dlx_exchange",true,false);
    }

    /***
     * @Description: common exchange
     * @return: org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public DirectExchange commonDirectExchange(){
        return new DirectExchange("common_exchange",true,false);
    }

    /***
     * @Description: binding
     * @return: org.springframework.amqp.core.Binding
     **/
    @Bean
    public Binding delayedBinding(){
        return BindingBuilder.bind(delayedQueue()).to(delayedDirectExchange()).with("delayed");
    }

    /***
     * @Description: binding
     * @return: org.springframework.amqp.core.Binding
     **/
    @Bean
    public Binding commonBinding(){
        return BindingBuilder.bind(commonQueue()).to(commonDirectExchange()).with("common_routingKey");
    }
}
