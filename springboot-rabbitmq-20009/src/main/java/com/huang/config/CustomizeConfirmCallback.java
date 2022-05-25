package com.huang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-29 15:14
 * Created by Huang
 * className: CustomizeConfirmCallback
 * Description: 消息从生产者到达exchange时返回ack，消息未到达exchange返回nack
 */
@Component
@Slf4j
public class CustomizeConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate template;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        } else {
            log.info("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            //TODO 将correlationData放入缓存中 利用定时任务再重新发送 or 直接发送
            if (correlationData instanceof CustomizeCorrelationData) {
                CustomizeCorrelationData messageCorrelationData = (CustomizeCorrelationData) correlationData;
                String exchange = messageCorrelationData.getExchange();
                Object message = messageCorrelationData.getMessage();
                String routingKey = messageCorrelationData.getRoutingKey();
                int retryCount = messageCorrelationData.getRetryCount();
                //重试次数+1
                messageCorrelationData.setRetryCount(retryCount + 1);
                template.convertSendAndReceive(exchange, routingKey, message, messageCorrelationData);
            }
        }
    }
}
