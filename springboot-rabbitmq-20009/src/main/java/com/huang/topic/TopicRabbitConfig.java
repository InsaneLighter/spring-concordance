package com.huang.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-27 16:59
 * Created by Huang
 * className: TopicsRabbitConfig
 * Description: routingKey不能有任意的,必须是由点分开的一串单词,可以由多个单词，但是有最大限制255bytes
 *              *：表示匹配任意一个单词
 *              #：表示匹配任意一个或多个单词。
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_Exchange");
    }

    @Bean
    public Queue topicQueue1(){
        return new Queue("topic_queue1");
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("topic_queue2");
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("huang.*");
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("huang.#");
    }
}
