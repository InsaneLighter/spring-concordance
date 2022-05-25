package com.huang.headers;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-24 13:57
 * Created by Huang
 * className: HeadersRabbitConfig
 * Description:
 *
 *
 */
@Configuration
public class HeadersRabbitConfig {

    @Bean
    public Channel channel(ConnectionFactory connectionFactory){
        return connectionFactory.createConnection().createChannel(false);
    }

    /***
     * @Description: headers exchange
     * @return: org.springframework.amqp.core.HeadersExchange
     **/
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange("headers_Exchange",true,false);
    }

    /***
     * @Description: 声明队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue headersQueue1(){
        return new Queue("headers_queue_1",true,false,false);
    }

    /***
     * @Description: 声明队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue headersQueue2(){
        return new Queue("headers_queue_2",true,false,false);
    }

    /***
     * @Description: 声明队列
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue headersQueue3(){
        return new Queue("headers_queue_3",true,false,false);
    }

    /***
     * @Description: 绑定队列与exchange
     *               where中指定key、value存在即分发
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Binding headersBindingQueue1(){
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).where("huang").matches("yiyi");
    }

    /***
     * @Description: 绑定队列与exchange
     *               whereAny 中定义参数存在一个即分发
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Binding headersBindingQueue2(){
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAny("a","b","c").exist();
    }

    /***
     * @Description: 绑定队列与exchange
     *               whereAll 中定义参数全部存在即分发消息
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Binding headersBindingQueue3(){
        return BindingBuilder.bind(headersQueue3()).to(headersExchange()).whereAll("a","b","c","d").exist();
    }
}
