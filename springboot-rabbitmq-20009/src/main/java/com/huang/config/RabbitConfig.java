package com.huang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time 2021-12-28 18:03
 * Created by Huang
 * className: RabbitConfig
 * Description:
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory,
                                   CustomizeConfirmCallback confirmCallback,
                                   CustomizeReturnsCallback CustomizeReturnsCallback){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback(confirmCallback);
        template.setReturnsCallback(CustomizeReturnsCallback);
        return template;
    }
}
