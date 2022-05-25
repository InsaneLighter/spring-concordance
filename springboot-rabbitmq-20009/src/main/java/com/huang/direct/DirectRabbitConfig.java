package com.huang.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-24 11:17
 * Created by Huang
 * className: DirectRabbitConfig
 * Description:  work类型exchange 多个消费者共同消费消息(一个queue消费完消息  另一个不能能消费此消息)
 *               需要指定routingKey
 *               消息路由到那些binding key与routing key完全匹配的Queue中
 */
@Configuration
public class DirectRabbitConfig {

    /***
     * @Description: exchange注册
     * @return: org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public DirectExchange directExchange(){
        //durable持久化
        //autoDelete exchange不再使用后是否自动删除
        return new DirectExchange("direct_Exchange",true,false);
    }

    @Bean
    public Queue queue(){
        return new Queue("direct_queue",true,false,false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("direct_routingKey");
    }
}
