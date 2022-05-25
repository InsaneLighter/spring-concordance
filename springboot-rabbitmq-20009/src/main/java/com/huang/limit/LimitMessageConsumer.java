package com.huang.limit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Time 2021-12-30 13:41
 * Created by Huang
 * className: LimitMessageConsumer
 * Description: 在非自动签收消息的前提下，如果一定数目的消息（通过基于consumer或者channel设置Qos的值）未被确认前，不进行新消息的消费
 */
@Component
@Slf4j
public class LimitMessageConsumer {
    @RabbitListener(queues = {"limit_queue"})
    public void consume(String msg, Channel channel, Message message) throws IOException {
        log.info("LimitMessageConsumer msg:{}",msg);
        try {
            //限流
            //prefetchSize：消息的大小限制，0 不限制。
            //prefetchCount：推送的消息数目。
            //global：true\false 是否将以上设置 应用于channel上
            channel.basicQos(0,3,false);
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("LimitMessageConsumer success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("LimitMessageConsumer fail");
        }

    }
}
