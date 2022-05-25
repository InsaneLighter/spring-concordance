package com.huang.dataConfirm;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-29 15:54
 * Created by Huang
 * className: DataConfirmRabbitConfig
 * Description:
 */
@Configuration
public class DataConfirmRabbitConfig {
    /***
     * @Description: exchange注册
     * @return: org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public DirectExchange dataConfirmExchange(){
        //durable持久化
        //autoDelete exchange不再使用后是否自动删除
        return new DirectExchange("dataConfirm_exchange",true,false);
    }

    @Bean
    public Queue dataConfirmQueue(){
        return new Queue("data_confirm_queue",true,false,false);
    }

    @Bean
    public Binding dataConfirmBinding(Queue dataConfirmQueue, DirectExchange dataConfirmExchange){
        return BindingBuilder.bind(dataConfirmQueue).to(dataConfirmExchange).with("data_confirm");
    }
}
