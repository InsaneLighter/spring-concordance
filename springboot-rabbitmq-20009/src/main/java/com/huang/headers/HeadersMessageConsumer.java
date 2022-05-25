package com.huang.headers;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Time 2021-12-24 14:05
 * Created by Huang
 * className: HeadersMessageConsumer
 * Description:
 */
@Component
@Slf4j
public class HeadersMessageConsumer {

    @RabbitListener(queues = {"headers_queue_1"})
    public void workProcessOne(String msg, Channel channel, Message message) throws IOException {
        log.info("workProcessOne msg:{}",msg);
        try {
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("workProcessOne success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("workProcessOne fail");
        }

    }

    @RabbitListener(queues = {"headers_queue_2"})
    public void workProcessTwo(String msg, Channel channel, Message message) throws IOException {
        log.info("workProcessTwo msg:{}",msg);
        try {
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("workProcessTwo success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("workProcessTwo fail");
        }

    }

    @RabbitListener(queues = {"headers_queue_3"})
    public void workProcessThree(String msg, Channel channel, Message message) throws IOException {
        log.info("workProcessThree msg:{}",msg);
        try {
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("workProcessThree success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("workProcessThree fail");
        }

    }
}
