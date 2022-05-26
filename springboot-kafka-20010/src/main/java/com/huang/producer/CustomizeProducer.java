package com.huang.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @Time 2022-01-24 15:45
 * Created by Huang
 * className: CustomizeProducer
 * Description:
 */
@Component
@Slf4j
public class CustomizeProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topicName,String key,String data) throws ExecutionException, InterruptedException {
        log.info("kafka send message");
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topicName, key, data);
        log.info("kafka send message end");
        SendResult<String, String> sendResult = listenableFuture.get();
        log.info("sendResult: {}",sendResult);
    }
}
