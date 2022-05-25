package com.huang.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-27 17:05
 * Created by Huang
 * className: TopicMessageProducer
 * Description:
 */
@Component
@Slf4j
public class TopicMessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void send(String exchange){
        for (int i = 1; i < 11; i++) {
            //输出时没有顺序，不需要等待，直接运行
            template.convertAndSend(exchange,"huang","huang time:"+i);
            template.convertAndSend(exchange,"huang.yiyi","huang.yiyi time:"+i);
            template.convertAndSend(exchange,"huang.yiyi.yiyi","huang.yiyi.yiyi time:"+i);
            //按照一定的顺序，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
            //template.convertSendAndReceive(exchange,routingKey,"message time:"+i);
        }
    }
}
