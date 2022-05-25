package com.huang.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Time 2021-12-24 14:04
 * Created by Huang
 * className: HeadersMessageProducer
 * Description:
 */
@Component
@Slf4j
public class HeadersMessageProducer {
    @Autowired
    private Channel channel;

    public void send(String exchange){
        for (int i = 1; i < 11; i++) {
            log.info("MessageProducer {}","message time:"+i);
            //输出时没有顺序，不需要等待，直接运行
            Map<String,Object> map = new HashMap<>();
            map.put("huang","yiyi");
            map.put("c","yiyi");
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            AMQP.BasicProperties basicProperties = builder.headers(map).build();
            try {
                log.info("HeadersMessageProducer 1 {}","message time:"+i);
                channel.basicPublish(exchange,"",basicProperties,("message time:"+i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
