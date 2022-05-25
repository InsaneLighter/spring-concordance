package com.huang.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-24 13:57
 * Created by Huang
 * className: FanoutRabbitConfig
 * Description: 发布订阅类型exchange 多个消费者同时消费消息(一个queue消费完消息  另一个也能消费此消息)
 *              不需要指定routingKey
 *              队列直接绑定该交换机（不需要指定routing key）
 */
@Configuration
public class FanoutRabbitConfig {

    /***
     * @Description: 声明fanout类型exchange
     * @return: org.springframework.amqp.core.FanoutExchange
     **/
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_Exchange",true,false);
    }

    /***
     * @Description: 声明队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout_queue_1",true,false,false);
    }

    /***
     * @Description: 声明队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout_queue_2",true,false,false);
    }

    /***
     * @Description: 绑定队列与exchange
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    /***
     * @Description: 绑定队列与exchange
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
