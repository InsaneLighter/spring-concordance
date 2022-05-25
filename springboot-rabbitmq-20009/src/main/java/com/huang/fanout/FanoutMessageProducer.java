package com.huang.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-24 14:04
 * Created by Huang
 * className: FanoutMessageProducer
 * Description:
 */
@Component
@Slf4j
public class FanoutMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(String exchange){
        for (int i = 1; i < 11; i++) {
            log.info("FanoutMessageProducer {}","message time:"+i);
            //输出时没有顺序，不需要等待，直接运行
            template.convertAndSend(exchange,"","message time:"+i);
            //按照一定的顺序，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
            //template.convertSendAndReceive(exchange,routingKey,"message time:"+i);
        }
    }
}
