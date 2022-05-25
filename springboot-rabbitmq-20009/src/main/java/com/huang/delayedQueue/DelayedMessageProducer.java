package com.huang.delayedQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-28 9:43
 * Created by Huang
 * className: DelayedMessageProducer
 * Description:
 */
@Component
@Slf4j
public class DelayedMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(String exchange,String routingKey){
        log.info("DelayedMessageProducer {}","dlx_message");
        //输出时没有顺序，不需要等待，直接运行
        template.convertAndSend(exchange,routingKey,"dlx_message");
    }
}
