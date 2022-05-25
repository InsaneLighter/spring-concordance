package com.huang.limit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-30 13:35
 * Created by Huang
 * className: LimitRabbitConfig
 * Description:
 */
@Configuration
public class LimitRabbitConfig {
    @Bean
    public DirectExchange limitDirectExchange(){
        return new DirectExchange("limit_direct_exchange",true,false);
    }

    @Bean
    public Queue limitQueue(){
        return new Queue("limit_queue",true,false,false);
    }

    @Bean
    public Binding limitBinding(DirectExchange limitDirectExchange,Queue limitQueue){
        return BindingBuilder.bind(limitQueue).to(limitDirectExchange).with("limit_routingKey");
    }
}
