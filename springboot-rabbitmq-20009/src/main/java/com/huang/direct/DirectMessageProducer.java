package com.huang.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Time 2021-12-24 11:13
 * Created by Huang
 * className: DirectMessageProducer
 * Description:
 */
@Component
@Slf4j
public class DirectMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(String exchange,String routingKey){
        for (int i = 1; i < 11; i++) {
            log.info("DirectMessageProducer {}","message time:"+i);
            //输出时没有顺序，不需要等待，直接运行
            CorrelationData correlationData = new CorrelationData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
            template.convertAndSend(exchange,routingKey,"message time:"+i,correlationData);
            //按照一定的顺序，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
            //template.convertSendAndReceive(exchange,routingKey,"message time:"+i);
        }
    }
}
