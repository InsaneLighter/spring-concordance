package com.huang.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-30 13:38
 * Created by Huang
 * className: LimitMessageProducer
 * Description:
 */
@Component
@Slf4j
public class LimitMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(String exchange,String routingKey){
        for (int i = 0; i < 20; i++) {
            String message = "limit message "+i;
            log.info("LimitMessageProducer {}",message);
            //输出时没有顺序，不需要等待，直接运行
            template.convertAndSend(exchange,routingKey,message);
        }
    }
}
