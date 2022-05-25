package com.huang.dataConfirm;

import com.huang.config.CustomizeConfirmCallback;
import com.huang.config.CustomizeCorrelationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Time 2021-12-29 15:27
 * Created by Huang
 * className: DataConfirmMessageProducer
 * Description:
 */
@Component
@Slf4j
public class DataConfirmMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(Object message,String exchange,String routingKey){
        log.info("消息 message({}) exchange({}) routingKey({})",message,exchange,routingKey);
        int retryCount = 0;
        CustomizeCorrelationData correlationData =
                new CustomizeCorrelationData(UUID.randomUUID().toString(),message,exchange,routingKey,retryCount);
        try {
            template.convertAndSend(exchange, routingKey, message, correlationData);
        } catch (AmqpConnectException e) {
            log.info("消息发送失败: {}", e.toString());
            //TODO 将correlationData放入缓存中 利用定时任务再重新发送
        }
    }
}
