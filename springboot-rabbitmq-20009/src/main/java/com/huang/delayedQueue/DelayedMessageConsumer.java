package com.huang.delayedQueue;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Time 2021-12-28 9:56
 * Created by Huang
 * className: DelayedMessageConsumer
 * Description:
 */
@Component
@Slf4j
public class DelayedMessageConsumer {
    @RabbitListener(queues = {"common_queue"})
    public void commonConsume(String msg, Channel channel, Message message) throws IOException {
        log.info("commonConsume msg:{}",msg);
        try {
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("commonConsume success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("commonConsume fail");
        }

    }

    @RabbitListener(queues = {"delay_queue"})
    public void dlxConsume(String msg, Channel channel, Message message) throws IOException {
        log.info("dlxConsume msg:{}",msg);
        try {
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("dlxConsume success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("dlxConsume fail");
        }

    }
}
