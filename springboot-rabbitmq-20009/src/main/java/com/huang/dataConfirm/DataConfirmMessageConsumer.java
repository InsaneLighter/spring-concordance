package com.huang.dataConfirm;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Time 2021-12-29 15:47
 * Created by Huang
 * className: DataConfirmMessageConsumer
 * Description:
 */
@Component
@Slf4j
public class DataConfirmMessageConsumer {
    @RabbitListener(queues = {"data_confirm_queue"})
    public void consume(Object msg, Channel channel, Message message) throws IOException {
        log.info("consume msg:{}",msg);
        try {
            String correlationDataId = message.getMessageProperties().getHeader("spring_returned_message_correlation");
            //入参
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("consume success");
        } catch (IOException e) {
            e.printStackTrace();
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.error("consume fail");
        }
    }
}
