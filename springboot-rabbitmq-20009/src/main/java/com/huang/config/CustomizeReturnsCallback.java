package com.huang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-29 15:39
 * Created by Huang
 * className: CustomizeReturnsCallback
 * Description: 消息进入exchange但未进入queue时会被调用
 */
@Component
@Slf4j
public class CustomizeReturnsCallback implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        String exchange = returned.getExchange();
        String routingKey = returned.getRoutingKey();
        Message message = returned.getMessage();
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.info("CustomizeReturnsCallback: exchange({}) routingKey({}) correlationId({})",exchange,routingKey,correlationId);
    }
}
